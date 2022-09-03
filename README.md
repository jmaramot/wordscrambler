# pepperstone word scrambler

Release Notes:

Language: Java
Build Tool: Maven

This initial commit only supports the required functionalities based on the exam criteria. Built jar is uploaded on this repo under jar folder.


Features:
1. Can accept up command line arguments
2. Supports multiline entry for input and dictionary file

Limitations:
1. Basic handling for file path checking
2. Only accepts full path for dictionary and input path arguments


Run comman syntax:
java -jar scrambled-strings.jar --dictionary "full exact path" --input "full exact path"

sample command in windows env:
java -jar scrambled-strings.jar --dictionary "C:\\pepperstone\\scrambler\\scrambled-strings\\src\\main\\resources\\sample_files\\dictionary.txt" --input "C:\\pepperstone\\scrambler\\scrambled-strings\\src\\main\\resources\\sample_files\\input.txt"
