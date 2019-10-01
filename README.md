# Auto-complete-spellcheck-system

This project designed an auto-complete & spellcheck system for querying movie names. 
We implemented two tries for this project. One is called wordTrie and the other one is called sentenceTrie. 


WordTrie is used to save all the words for spellcheck and sentenceTrie is used to do the auto-complete job.
When running the user interface, you need two args for main function, 
the first one is “dictionary.txt” which is used for generating wordTrie, 
the second one is “movies.csv” which is used to generate sentenceTrie. 

When asked for input, you can input a string every time.
For example, if you want to search for the movie “little lord fauntleroy”, 
you can first input “l” “litt” or “little lor” or something else, and hit enter, 
we will find the top 5 frequent movies from the trie and you can choose one by entering the number of the return names. 
If you did not find what you want, you can type in 9 and then continue your input, 
if we cannot find anything that matches your input in our sentenceTrie, 
we will try to match it with our wordTrie as long as the input does not contain whitespaces. 
If we still cannot find anything, we will tell you that we cannot find it in our database, 
just like when something rare is entered into the google search bar, google will not provide any suggestions 
in the drop-down menu. 

If you finish your input, please add a “#” at the end to indicate that you have finished. 
And we will then do the spell-check for your input with our wordTrie. 
We will break up your input by whitespace and check word by word. If we found something that might be wrong, we will print that in our console.
Note that for simplicity of the process, we only accept English characters (upper case is fine, 
but we will change it into lower case and then try to match), whitespaces and #, other characters 
including digits are considered invalid input.
