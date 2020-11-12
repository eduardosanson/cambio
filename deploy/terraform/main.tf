provider "aws" {
  profile = "personal-account"
  region = "us-east-1"
}

data "terraform_remote_state" "init-infra" {
  backend = "s3"

  workspace = terraform.workspace

  config = {
    bucket = "prod-infra-init-terraform.state"
    region = "us-east-1"
    key    = "infra-application/terraform.tfstate"
    profile = "personal-account"
  }
}

data "terraform_remote_state" "cluster" {
  backend = "s3"

  workspace = terraform.workspace

  config = {
    bucket = "prod-infra-init-terraform.state"
    region = "us-east-1"
    key    = "service/project/terraform.tfstate"
    profile = "personal-account"
  }
}

module "role" {
  source       = "github.com/eduardosanson/terraform-template-modules//modules/ecs-container-service/role?ref=master"
  cluster_name = var.cluster_name
  app-name     = var.application_name
}

module "auto-scalling" {
  source = "github.com/eduardosanson/terraform-template-modules//modules/ecs-container-service/auto-scalling?ref=master"
  cluster_name = data.aws_ecs_cluster.ecs_cluster.cluster_name
  service_name = var.application_name
  max_capacity = var.max_capacity
  min_capacity = var.min_capacity
}

module "lb" {
  source = "github.com/eduardosanson/terraform-template-modules//modules/ecs-container-service/lb?ref=master"
  load_balancer_arn = data.terraform_remote_state.cluster.outputs.alb_arn
  vpc_id            = data.terraform_remote_state.init-infra.outputs.vpc_id
  container_name    = var.application_name
  lb_port_redirect  = var.application_port
  health_check_path = var.health_check
}

module "service" {
  source = "github.com/eduardosanson/terraform-template-modules//modules/ecs-container-service/service?ref=master"
  role_execution_arn      = module.role.ecs-execution-role-arn
  role_service_arn        = module.role.ecs-service-role-arn
  lb_target_group_arn     = module.lb.lb_target_group_arn
  ecs_cluster_id          = data.aws_ecs_cluster.ecs_cluster.id
  cluster_name            = data.aws_ecs_cluster.ecs_cluster.cluster_name
  container_name          = var.application_name
  container_image         = var.application_image
  lb_port_redirect        = var.application_port
  task_defination_path    = var.task_definition_path
}