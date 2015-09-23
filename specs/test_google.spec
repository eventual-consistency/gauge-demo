Steve's Example Tests
=====================

This is a test spec(ification). It's written in business-english. Testers can write test cases
in business English which will be executed. To run this file execute

	gauge specs specs/test_google.spec
	

Search Google
--------------
When searching Google for the president of the United States, the current
sitting president should be shown on the results page.

tags: hello world, first test

* Navigate to "http://www.google.com/"
* Type "Who is the US President" into "lst-ib"
* Wait up to "10" seconds for element "search"
* Page should contain text "Barack Obama"

Search Google and Fail
--------------
When searching for the president of the United States, Steve Gray should not
be there. This is also a good test to show the screen-shot feature in action.

tags: hello world, first test

* Navigate to "http://www.google.com/"
* Type "Who is the US President" into "lst-ib"
* Wait up to "10" seconds for element "search"
* Page should contain text "Steve Gray"