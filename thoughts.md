# Thoughts

## General Rules

There are 26 stacks in Kipple (a-z). There is also @ which is a special stack.
Pushing any value onto @ will push the ASCII values of the value intead. For instance
100>@ will push 49 48 48 so when we do @>o, it will ouput 100

Accepted operators +, -, ><, ? Anything which does not follow these operators should be ignored.
There is also a-z and (). The loop construct is (). 

So if we want to output and entire stack, we would have to do something like:

`(a>o)`

This will push everything from the `a` stack onto the `o` stack which is output.

### How do `+, -` work?
The `+` operator works by taking the sum of the topmost item on the stack and the operand and pushing it onto
the stack. For example, if we have a stack `a` which is `[1 2]` (1 is the top) and we do something like `a+2`
then the stack will contain `[3 1 2]`. 

If we are adding something to an empty stack then we just push the operand onto the stack. 

If we add a stack onto another stack then we take the top most value of the stack, pop it onto the other stack.

### How does `<>` work?
1. If `<` then take the topmost value of `b` and push it onto `a`
2. If `>` then take the topmost value of `b` and push it onto `a`

With these two rules defined, we can easily figure out things like

`a>b<c`

since we can "read" it as "push a onto b and capture c".

### How does `?` work?
The `?` operator looks at the top most value of the stack and determine whether or not the value is 0. If the value
is 0 then it clears the stack and if the value is not 0 then it does nothing. 

## Thoughts on Grammar
 
 We need to define some syntax rules for this language since they are implicit. For example, the following code is valid:
 
 `12>a<56 25>b<698`
 
 while this is not valid
  
  `12>a<56<a`
  
Since it's ambiguous as to what is being pushed where. (thought: what happens if we do a<a?) Furthermore, the following is the same

`a<b` and `b>a`

This can be read as "a _captures_ b" and "b is _pushed_ onto a" which are both  the same thing. So we can have two rules for the
push operands. 

## Scanning and Parsing
The most difficult thing about this operation is how to scan and parse the data. For the moment, the scanning is done by just reading
the file in as a string. 

My thoughts are to split the file from newline characters and then scan the file line by line and look for operands. 

**Regular Expressions!!!!** I can use regular expressions to ~~parse~~ scan the data ... The regular expression has to look for <>?+- and ignore 
the rest. 

### Regular Expressions
There are some cases where regular expressions will work beautiful. Take for example the following: 

`/\(.+?\s*)/g` and `[a-z]?[0-9]*>[a-z]?<[a-z]?[0-9]+`

but it has a number of drawbacks. For example, the first regex will capture anything in brackets and the second one fails for
minor syntactical errors like `a1>a<34`.

Once we have gotten that information then we can remove things that don't make sense. For example, aa or +a or 2+aa

### Some (obvious) Rules
1. If the line starts with `#` then ignore the line
2. If the line contains no operands then ignore that line
    - How can one test for that?
    
## More thoughts

Operations need to be conducted recursively; it would nice to read data as a tree
and then apply interpret the data through the tree.
