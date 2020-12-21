# Variables checker
    Very strange piece of software
    checking validity of variable names in a very special context. 
	
	The allowed names are defined by a grammar 
	which is given by a ruleset in a rulesetfile `xxx.rls`. 
	An example for a ruleset file is given in 
	[start.rls](src/test/resources/eu/simuline/names/start.rls).
	<!-- TBD: make the link work -->
	
	In the rules file, lines starting with `//` are comments. 
	A ruleset has the form 
	```
	<name> {
	...
	}
	```
	, where `<name>` is the name and `...` represents the set of rules, 
	each rule given by a line. 
	A line has the form 
	```
	name --> (...)
	```
	optionally preceeded by `|->` and/or followed by `->|`. 
	All names `<name>` are so called categories 
	each of which is defined in an according category file `<name>.cat` 
	which must reside in the same directory as the ruleset file. 
	
	TBD: go on here with the description. 
	
	The software can be started from the base directory with 
	```
	java -jar target/varnames-0.1-SNAPSHOT-jar-with-dependencies.jar src/test/resources/eu/simuline/name/start.rls
	```
