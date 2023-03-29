import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP e buscar os top filmes
        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var Request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(Request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //Extrair só os dados que interessa (Titulo, Imagem, Classificação)

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        

        //Exibir e manipular os dados

        for (int i=0; i<4; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[1mTitulo\u001b[m:\u001b[3m " + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[1mURLCapa\u001b[m:\u001b[3m " + filme.get("image") + "\u001b[m");
            //System.out.println( filme.get("imDbRating"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int estrelas = (int) classificacao;
            
            for (int n = 1; n <= estrelas; n++) {
                System.out.print("\u001b[45m⭐\u001b[m");
            }

            System.out.println("\n");
            
        }
    }
}
