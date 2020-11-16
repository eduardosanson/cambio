module "priority_queue" {
  source                    = "terraform-aws-modules/sqs/aws"
  version                   = "~> 2.0"
  name                      = "${var.application_name}-priority-queue"
  message_retention_seconds = 60 * 60
  redrive_policy = jsonencode({
    deadLetterTargetArn = module.sqs_dlq.this_sqs_queue_arn
    maxReceiveCount     = 5
  })
  tags = {
    Challenge = "priority_queue"
  }
}

module "sqs_dlq" {
  source                    = "terraform-aws-modules/sqs/aws"
  version                   = "~> 2.0"
  name                      = "${var.application_name}-priority-queue-dlq"
  message_retention_seconds = 1209600
  tags                      = var.tags
}

module "secondary_queue" {
  source                    = "terraform-aws-modules/sqs/aws"
  version                   = "~> 2.0"
  name                      = "${var.application_name}-secondary-queue"
  message_retention_seconds = 60 * 60
  redrive_policy = jsonencode({
    deadLetterTargetArn = module.sqs_dlq.this_sqs_queue_arn
    maxReceiveCount     = 5
  })
  tags = var.tags
}

resource "aws_iam_role_policy_attachment" "ecs-execution-role-attachment" {
  role       = var.task_role
  policy_arn = aws_iam_policy.task_policy.arn
}