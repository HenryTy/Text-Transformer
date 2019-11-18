package pl.put.poznan.transformer.rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.error.BadTransformationException;
import pl.put.poznan.transformer.logic.SequenceTransformer;

@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper") String[] transforms) {

        try {
            SequenceTransformer transformer = new SequenceTransformer(transforms);
            return new ResponseEntity<>(transformer.transform(text), HttpStatus.OK);
        } catch (BadTransformationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}


