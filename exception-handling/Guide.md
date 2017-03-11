Exception Handling Guidelines
====
* Do not use Exception for Control Flow
* Throw fast catch late
* Define Exception boundary beyond which exception should not bubble up without handling

Checked Exception
---
* Throw Checked Exception if there is high probability that client can recover from it
* Add enough details in check exception for recovery

Unchecked Exception
---
* Exception boundary SHOULD have way to handle Unchecked Exception


