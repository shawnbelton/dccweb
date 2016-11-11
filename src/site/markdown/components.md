DCC Web Components
==================

DCC Web is built using a number of components that each have a distinct 
function of the application.

dcc-webapp
----------

The dcc-webapp is the user interface component of the application it is 
this that provides the rest services for the angularJS web interface and
also serves the web pages, stylesheets and javascript to the browser.

dcc-core
--------

The dcc-core provides most of the core functionality.

dcc-data
--------

dcc-data provides the data persistence functionality.

decoder-definitions
-------------------

decoder-definitions contains classes for reading CV values by name for 
decoders and also the definitions of decoders.

dcc-interface
-------------

The dcc-interface contains classes to pass messages from the core of the
application to the different dcc system interfaces.

demo-interface
--------------

The demo interface is for testing core functionality without the need to
connect to an actual DCC System.

nce-interface
-------------

The nce-interface is the component that handles incoming messages and 
converts them to the NCE messages that are sent over the serial connection
to the NCE System.