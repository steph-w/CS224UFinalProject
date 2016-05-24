import re
f = open("50_male_verb.txt", "r")
for line in f:
	word_count = line.split()
	word_count[1] = re.sub(r"_VB", "", word_count[1])
	print (word_count[1] + ": " + word_count[0])