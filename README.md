# FlyChat
## Goals
This project aims to implement a IM server based on netty framework.
## Description
The IM server can provide message functions for both peer-to-peer purpose and client-customer purpose.

When used for peer-to-peer purpose, users can add friends with their accounts and then talk to each other.
They can also choose to create a group and then chat in the group, with everybody in the group receiving messages from everybody else.

When used for the client-customer service, users can login as client or customer.
Clients are configured with limited numbers at the same time, while customers can only talk to clients when they are online. Or they can 
choose to leave a message when no clients are available.
