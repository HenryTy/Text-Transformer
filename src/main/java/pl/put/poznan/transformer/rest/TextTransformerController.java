package pl.put.poznan.transformer.rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.error.BadAnalysisException;
import pl.put.poznan.transformer.error.BadTransformationException;
import pl.put.poznan.transformer.logic.SequenceTransformer;
import pl.put.poznan.transformer.logic.TextAnalysisExecutor;

@RestController
public class TextTransformerController {

    @RequestMapping(path = "/transform/{text}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> transformText(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper") String[] transforms) {

        try {
            SequenceTransformer transformer = new SequenceTransformer(transforms);
            return new ResponseEntity<>(transformer.transform(text), HttpStatus.OK);
        } catch (BadTransformationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/analyze/{text}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> analyzeText(@PathVariable String text,
                                                @RequestParam(value="analysis") String analysisName) {

        try {
            TextAnalysisExecutor textAnalysisExecutor = new TextAnalysisExecutor();
            String analysisResult = textAnalysisExecutor.analyseText(analysisName, text);
            return new ResponseEntity<>(analysisResult, HttpStatus.OK);
        } catch (BadAnalysisException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}


