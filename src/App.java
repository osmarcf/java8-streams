import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Curso {
    String nome;
    int quantidadeAlunos;

    Curso(String nome, int quantidadeAlunos) {
        this.nome = nome;
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        List<Curso> lista = new ArrayList<>();
        lista.add(new Curso("Java", 40));
        lista.add(new Curso("Javascript", 60));
        lista.add(new Curso("C++", 20));
        lista.add(new Curso("Python", 80));

        lista.sort(Comparator.comparing(Curso::getQuantidadeAlunos));

        //lista.forEach(c -> System.out.println(c.getNome()));

        int quantidadeAlunosCursosMaisQue50 = lista.stream()
            .filter(c -> c.getQuantidadeAlunos() >= 50)
            .mapToInt(Curso::getQuantidadeAlunos)
            .sum();
            //.map(Curso::getQuantidadeAlunos)
            //.forEach(System.out::println);
            //.forEach(c -> System.out.println(c.getNome()));

        System.out.println(quantidadeAlunosCursosMaisQue50);

        /*
        lista.stream()
            .map(Curso::getNome)
            .forEach(System.out::println);
        */
        /*
        Optional<Curso> optionalCurso = lista.stream()
            .filter(c -> c.getQuantidadeAlunos() >= 50)
            .findAny();

        Curso curso = optionalCurso.orElse(null);
        System.out.println(curso.getNome());
        optionalCurso.ifPresent(c -> System.out.println(c.getNome()));
        */

        /*
        lista.stream()
            .filter(c -> c.getQuantidadeAlunos() >= 50)
            .findAny()
            .ifPresent(c -> System.out.println(c.getNome()));
        */

        /*
        lista.stream()
            .filter(c -> c.getQuantidadeAlunos() >= 50)
            .mapToDouble(c -> c.getQuantidadeAlunos())
            .average()
            .ifPresent(System.out::println);
        */

        /*
        List<Curso> novaLista = lista.stream()
            .filter(c -> c.getQuantidadeAlunos() >= 50)
            .collect(Collectors.toList());
        */
        
        lista.parallelStream()
            .filter(c -> c.getQuantidadeAlunos() >= 50)
            .collect(Collectors.toMap(c -> c.getNome(), c -> c.getQuantidadeAlunos() ) )
            .forEach( (chave, valor) -> System.out.println("O curso de " + chave + " tem " + valor + " alunos." ));
    }
}
