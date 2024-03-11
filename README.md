# cdrpoc
This is a proof of concept that displays Asterisk's CDR using [Ktor](https://ktor.io/) and
[Refine](https://refine.dev/). The aim is to evaluate the feasibility of developing
administrative tooling for Asterisk with this or a similar software stack.

## Functionality
* Display CDR in list and form (single record) views.
* Provide a user interface to administrate user access to the platform.
* Protect API records with username and password based login.
* Handle Authorization.

### Permission Schema
* **Administrator**
    * Has read and write access to users.
    * Has read access to CDR.
* **User**
    * Has read access to CDR.
