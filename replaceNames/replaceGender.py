from GenderClassifier import GenderClassifier
import sys

def loadList(file_name):
    """Loads text files as lists of lines. Used in evaluation."""
    with open(file_name) as f:
        l = [line.strip() for line in f]
    return l

def main():
	if len(sys.argv) < 3:
		print "PLEASE INCLUDE \nARG 1: file to read from \nARG 2: file to write to"
		return

	readFrom = sys.argv[1]
	writeTo = sys.argv[2]

	gender = GenderClassifier()

	l = loadList(readFrom)
	f = open(writeTo,'w')

	for line in l:
		listOfWords = line.split(" ")
		for i in range(0, len(listOfWords)):
			word = listOfWords[i]
			wordEnd = ""
			if len(word) > 0 and word[0].isupper():
				word = word.lower()
				if "'" in word and word != "don't" and word != "didn't":
					word = word[:word.index("'")]
					wordEnd = "'s"
				elif "." in word or "?" in word or "," in word or "!" in word or ":" in word or ";" in word:
					wordEnd = word[len(word) - 1:]
					word = word[:len(word) - 1]
				g = gender.guessGender(word)
				if g == "m":
					listOfWords[i] = "Man" + wordEnd
					print line
					line = " ".join(listOfWords)
					print line
				elif g == "f":
					print line
					listOfWords[i] = "Woman" + wordEnd
					line = " ".join(listOfWords)
					print line

		f.write(line + '\n') # python will convert \n to os.linesep
	f.close()

if __name__ == '__main__':
    main()

