(ns kinterpreter.kipple-interpreter)

;; An interpreter for the esoteric Kipple language
;; This code was written in response to a challenge found on codegolf.stackexchange.com
;; http://codegolf.stackexchange.com/questions/84914/interpret-kipple

;; NOTE: There are 26 stacks in Kipple (a-z). There is also @ which is a special stack.
;; Pushing any value onto @ will push the ASCII values of the value intead. For instance
;; 100>@ will push 49 48 48 so when we do @>o, it will ouput 100

;; Accepted operators +, -, ><, ? Anything which does not follow these operators should be ignored.
;; There is also a-z and ()

;; 1. Recieve input
;; 2. Parse the data

;; Thoughts on 2. ... Should we use a tree or a list of lists?
;;  Let's run with a tree
;; Thoughts ... if it's a tree structure then do we have:
;; {:a [values] :b ...} ?

;; We need a parser and a scanner and then a tokenizer

(defn recieve-code [path]
  "Reads the file based on the given path and returns a string."
  (slurp path))

(defn convert-to-ascii [ascii-code]
  "Converts ascii to a string. Expects an int and returns a string"
  (str (char ascii-code)))

(defn convert-to-decimal [decimal-code]
  "Expects a string and returns a collection "
  (map int decimal-code))

(defn onto-stack [stack value]
  (cons value stack))

(defn pop-stack [stack]
  (pop stack))

(defn clear? [stack]
  (if (zero? (first stack))
    []
    stack))


(prn (recieve-code "C:\\Users\\Neel\\Desktop\\kipple1.01\\samples\\test.k"))

