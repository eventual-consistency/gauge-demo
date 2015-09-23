Steve's Example Tests
=====================

This is a test spec(ification). It's written in business-english. Testers can write test cases
in business English which will be executed. To run this file execute

	gauge specs specs/test_google.spec
	

Search Google
--------------
Validate that the Google homepage loads without errors.

tags: hello world, first test

* Navigate to "http://www.google.com/"
* Type "Who is the US President" into "lst-ib"
* Wait up to "10" seconds for element "search"
* Page should contain text "Barack Obama"