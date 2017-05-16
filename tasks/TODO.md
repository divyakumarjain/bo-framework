Exception Handling
===
* [X] Standard UnChecked Exception
* [X] Standard Checked Exception
* [ ] Exception Handling and HTTP status reporting this needs to be done in Endpoint implementation

Handling Entity Relationships
=============================

Requirement
-----------
* [ ] API Endpoint for navigating relationships
* [ ] CRD of relationships 
* [ ] Update of relationships with entity merge and list merge


Hypermedia Linking Relationships
================================

Requirements
------------

1. Navigable Links in Representation
2. Support for HAL
3. Support creation and modification of relationship objects.


TODO
----
1. In Progress - Complete implementation and Unit test of REST API layer
	* [X] Relationship navigation using read method on entity
	* [ ] Relationship navigation using method on managers.
2. Support PUT and POST method on the relationship endpoint.
3. DAL Layer
4 [ ] Switching Config between Hypermedia and non Hypermedia Endpoint.
    * [ ] Can be easily implemented for Spring boot auto-configuration

Security
===
* Action, Permission, Role Hierarchy
* Allowed Action
* Pluggable Security provider
* Read allowed field based on roles
* Update allowed field based on roles


Handling Search Request
=============================

Requirement
-----------
1. API Search entity
    * [X] Support Pure database approach
    * [ ] Support Hibernate Search
2. Enhance API for search across relationships (Good Feature to have)

Entity History
==============

Requirement
-----------
1. Capture history of Entity with timestamp and user who modified

Event Handling
==============

Requirement
-----------
* Raising Events
* Routing Events
* Reporting
* Performance Events

Validation
==========

Requirement
-----------
* Validation of Create, Update, Delete
* Validation for Custom Business Operation

Guice Demo
===

State Machine
===
* Validation based on state
* State as a field in BO
* State Transition POST support in controller and HATEOAS link in Representation

Integration
===

Java 8
======

* Support Java 8 Streaming




