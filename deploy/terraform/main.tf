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

module "alb-target-group" {
  source = "github.com/eduardosanson/terraform-template-modules//modules/lb-target-group?ref=master"
  load_balancer_arn = data.terraform_remote_state.cluster.outputs.lb_arn
  vpc_id            = data.terraform_remote_state.init-infra.outputs.vpc_id
  target_group_name = var.application_name
  lb_port_redirect  = var.application_port
  health_check_path = var.health_check
}

module "nlb" {
  source = "github.com/eduardosanson/terraform-template-modules//modules/lb?ref=master"
  sg_id           = data.terraform_remote_state.init-infra.outputs.sg
  vpc_subnets     = data.terraform_remote_state.init-infra.outputs.public_subnet
  lb-type         = "network"
  lb-name         = var.application_name
}

module "nlb-target-group" {
  source = "./modules/lb-target-group"
  load_balancer_arn = module.nlb.lb_arn
  vpc_id            = data.terraform_remote_state.init-infra.outputs.vpc_id
  target_group_name = var.application_name
  lb_port_redirect  = var.application_port
  protocol          = "TCP"
}

module "service" {
  source = "github.com/eduardosanson/terraform-template-modules//modules/ecs-container-service/service?ref=master"
  role_execution_arn      = module.role.ecs-execution-role-arn
  role_service_arn        = module.role.ecs-service-role-arn
  lb_target_group_arn     = [module.alb-target-group.lb_target_group_arn]
  ecs_cluster_id          = data.aws_ecs_cluster.ecs_cluster.id
  cluster_name            = data.aws_ecs_cluster.ecs_cluster.cluster_name
  container_name          = var.application_name
  container_image         = var.application_image
  lb_port_redirect        = var.application_port
  task_defination_path    = var.task_definition_path
}

module "api-gw" {
  source = "./modules/api-gw"
  application_name = var.application_name
  swagger_path = "file/swagger.yml"
  nlb_domain_name = data.terraform_remote_state.cluster.outputs.lb_dns
  nlb_listener_port = var.application_port
  lb_arn = module.nlb.lb_arn
}

module "sqs" {
  source = "./modules/sqs"
  tags = { Challenge = "priority_queue" }
  task_role = module.role.ecs-service-role-name
  application_name = var.application_name
}