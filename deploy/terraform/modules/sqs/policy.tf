///* SQS DELIVERY POLICY */
//data "aws_iam_policy_document" "sqs_policy" {
//
//  statement {
//    sid     = "SQSExecution"
//    effect  = "Allow"
//    actions = ["SQS:*", ]
//    principals {
//      type        = "AWS"
//      identifiers = ["*"]
//    }
//    resources = [module.priority_queue.this_sqs_queue_arn, module.secondary_queue.this_sqs_queue_arn,
//                 module.sqs_dlq.this_sqs_queue_arn]
//  }
//
//}
//
//resource "aws_sqs_queue_policy" "sqs_policy" {
//  queue_url = module.priority_queue.this_sqs_queue_id
//  policy = data.aws_iam_policy_document.sqs_policy.json
//}

data "aws_iam_policy_document" "task_policy_doc" {

  statement {
    effect    = "Allow"
    actions = ["SQS:*"]
    resources = [module.priority_queue.this_sqs_queue_arn, module.secondary_queue.this_sqs_queue_arn,
      module.sqs_dlq.this_sqs_queue_arn]
  }

}
resource "aws_iam_policy" "task_policy" {
  name   = "${var.application_name}-task-policy"
  path   = "/"
  policy = data.aws_iam_policy_document.task_policy_doc.json
}