Operating System Family: windows
Virtual Machine Architecture: x64
Virtual Machine Name: hotspot
========================================================================================================================
Template: base
Version: 3.1.2.RELEASE
Build Date: 20150910194706

* Sets Xmx to 512M
* Sets Xss to 256K
* Adds a control script to the instance
* Adds the Windows service wrapper libraries
* Adds a default jmxremote configuration with a read/write user called 'admin' with a generated password
* Adds a default JULI logging configuration
* Adds a default server configuration containing:
	* A JRE memory leak prevention listener
	* A tc Runtime Deployer listener
	* A JMX socket listener
	* A LockOutRealm to prevent attempts to guess user passwords via a brute-force attack
	* An in-memory user database
	* A threadpool that has up to 300 threads
	* A host that uses 'webapps' as its app base
	* An AccessLogValve
* Adds a default Tomcat user configuration that is empty
* Adds an init.d script configured to start the instance as a specific user
* Adds a root web application
========================================================================================================================
Template: base-tomcat-7
Version: 3.1.2.RELEASE
Build Date: 20150910194706

* Adds Tomcat 7-specific ThreadLocalLeakPreventionListener
* Adds Tomcat 7-specific catalina.properties
* Adds Tomcat 7-specific default catalina.policy to be used when starting with the -security option
* Adds Tomcat 7-specific JspServlet configuration
* Adds Tomcat 7-specific web-app declaration
========================================================================================================================
Template: bio
Version: 3.1.2.RELEASE
Build Date: 20150910194706

* Adds a Blocking IO (BIO) connector for HTTP
========================================================================================================================
Template: bio-ssl
Version: 3.1.2.RELEASE
Build Date: 20150910194706

* Adds a Blocking IO (BIO) connector for HTTPS
* Adds sample certificate and key files that can be used to test the SSL configuration
