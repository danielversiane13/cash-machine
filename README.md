Openjdk version: 11.0.11

# Api documentation:

## Current Account Controller

GET `/current-account/{machineId}` - Get quantity of each money bills

POST `/current-account/{machineId}/money-bills/{moneyBillsId}` - Add quantity for one money bills

POST `/current-account/{machineId}/withdraw` - Withdraw balance

## Machine Controller

GET `/machines` - Find all machines with paging

POST `/machines` - Create a machine

GET `/machines/{machineId}` - Find a machine by id

PUT `/machines/{machineId}` - Update a machine by id

## Machine Withdraw Controller

GET `/machines/{machineId}/withdraws` - List withdrawals by machine

## Money Bills Controller

GET `/money-bills` - List money bills
