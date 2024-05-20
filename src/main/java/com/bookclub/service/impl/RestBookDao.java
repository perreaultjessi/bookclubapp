import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.JsonPath;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.jayway.jsonpath.Configuration;
import java.util.ArrayList;
import java.util.List;

public class RestBookDao implements BookDao
{

    @Override
    public List<Book> list(String key)
    {
        Object doc = getBooksDoc(key);
        List<Book> books = new ArrayList<>();
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        for (int index = 0; index < titles.size(); index++)
        {
            books.add(new Book(isbns.get(index), titles.get(index), infoUrls.get(index)));
        }
        return books;
    }

    @Override
    public Book find(String key)
    {
        Object doc = getBooksDoc(key);
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<String> pages = JsonPath.read(doc, "$..details.number_of_pages");
        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String desc = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? Integer.parseInt(pages.get(0)) : 0;
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        return new Book(isbn, title, desc, infoUrl, numOfPages);
    }

    private Object getBooksDoc(String isbnString)
    {
        String openLibraryUrl = "https://openlibrary.org/api/books";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");
        ResponseEntity<String> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
        String jsonBookList = response.getBody();
        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBookList);
    }
}
