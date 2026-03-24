# Communication Gateway - System Design Problem

## Overview

Design and implement a backend module for a **Unified Communication Gateway** that enables multiple applications (such as web apps, mobile apps, and admin dashboards) to send notifications to users through different communication channels.

The system should abstract the complexities of individual communication channels and provide a single, consistent interface for sending notifications.


## Problem Statement

Your task is to design a system that processes incoming notification requests and routes them to the appropriate communication channel based on the request data.

The system must support multiple channels, each with its own behavior, constraints, and validation logic.


## Supported Channels (Initial Version)

* Email
* SMS
* Push Notifications


## Future Expansion (Must Be Supported by Design)

The system should be designed in a way that allows easy addition of new channels such as:

* WhatsApp
* Slack
* In-app messaging


## Input Format

Each notification request will be received in the following structure:

```
{
  "channel": "EMAIL",
  "recipient": "user@example.com",
  "message": "Your order has been shipped",
  "priority": "HIGH"
}
```


## Functional Requirements

1. The system must:

   * Identify the communication channel from the request
   * Route the request to the appropriate channel handler
   * Send the notification

2. Each communication channel may have unique behavior:

   * Email may require formatting (subject, body structure)
   * SMS may enforce character limits
   * Push notifications may include additional metadata

3. The system should validate requests:

   * Channel-specific validation (e.g., email format, SMS length)
   * Reject invalid requests with meaningful errors

4. The system should handle:

   * Unsupported channels gracefully
   * Failures in message delivery

5. The system must ensure:

   * Clear separation of concerns
   * Clean and maintainable code structure


## Non-Functional Requirements

* The system should be **easily extensible** to support new communication channels
* The core logic should remain **independent of channel-specific implementations**
* The design should follow **clean architecture principles**
* The system should be **scalable and maintainable**


## Constraints

* Adding a new communication channel should require minimal changes to existing code
* Avoid tight coupling between components
* The calling layer (e.g., controller/service) should not be aware of channel-specific logic


## Edge Cases to Consider

* Unsupported or unknown channel type
* Missing required fields (e.g., recipient, message)
* Invalid input formats (e.g., malformed email)
* Message delivery failures
* Handling high-priority notifications differently


## Expectations

* Design structure and clarity
* Proper use of design patterns
* Separation of concerns
* Extensibility of the system
* Code readability and maintainability
* Handling of edge cases




