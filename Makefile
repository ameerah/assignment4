# makefile

# bash

JAVAC = javac
SOURCES = assignment4.java
SUFFIXES = .class .java
FLAGS = -g

assignment4.class: assignment4.java
	$(JAVAC) $(FLAGS) assignment4.java

clean:
	@rm *.class

run:
	java assignment4
