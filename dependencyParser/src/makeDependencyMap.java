
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.*;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

class makeDependencyMap {

  /** This example shows a few more ways of providing input to a parser.
   *
   */
  public static void main(String[] args) throws IOException {
    String grammar = args.length > 0 ? args[0] : "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
    String[] options = { "-maxLength", "80", "-retainTmpSubcategories" };
    String textFile = "female_labels_male_author.txt";
    LexicalizedParser lp = LexicalizedParser.loadModel(grammar, options);
    TreebankLanguagePack tlp = lp.getOp().langpack();
    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
    
    PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
    
    for (List<HasWord> sentence : new DocumentPreprocessor(textFile)) {
        Tree parse = lp.apply(sentence);
//        parse.pennPrint();
//        System.out.println();

        if (gsf != null) {
          GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
          List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
          writer.println(tdl);
        
//			This code will only write the dependencies with male and female
//          List<TypedDependency> mftdl = new ArrayList<TypedDependency>();
//          for(TypedDependency d : tdl){
//			  Syntax for taking apart IndexedWords
//        	  System.out.println(d);
//        	  System.out.println(d.gov());
//        	  System.out.println(d.gov().originalText());
//        	  System.out.println(d.gov().tag());
//        	  
//        	  String gText = d.gov().originalText();
//        	  String dText = d.dep().originalText();
//        	  if(gText.equals("00FEMALE00") || gText.equals("00MALE00")
//        	  || dText.equals("00FEMALE00") || dText.equals("00MALE00")){
//        		  mftdl.add(d);
//        	  }
          }
//          writer.println(mftdl);

//        }
      }
    writer.close();
  }

  private makeDependencyMap() {} // static methods only

}
