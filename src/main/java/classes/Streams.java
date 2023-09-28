package classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author thiagoabaguiar
 */
public class Streams {

    public static void main(String[] args) {
 
        List<Curso> cursos = new ArrayList<Curso>();
        
        cursos.add(new Curso("Java", 50));
        cursos.add(new Curso("PHP", 20));
        cursos.add(new Curso("Python", 80));
        
        cursos.sort(Comparator.comparing(c -> c.getAlunos())); // Lambda
        cursos.sort(Comparator.comparing(Curso::getAlunos)); // Method Reference
        
        cursos.forEach(c -> System.out.println(c.getNome())); // Lambda
        cursos.forEach(System.out::println); // Method Reference
        
        
        // Imprimindo cursos com mais de 30 alunos:        
        // 1) usando Lambda
        cursos.stream()
                .filter(c -> c.getAlunos() >= 30)
                .forEach(c -> System.out.println(c.getNome()));
        
        // 2) usando Method Reference - forma 1
        Stream cursos30 = cursos.stream()
                .filter(c -> c.getAlunos() >= 30);        
        cursos30.forEach(System.out::println);
        
        // 3) usando Method Reference - forma 2
        cursos.stream()
                .filter(c -> c.getAlunos() >= 30)
                .forEach(System.out::println);
        
        
        // Imprimindo a qtde de alunos de cursos com mais de 30 alunos:        
        cursos.stream()
                .filter(c -> c.getAlunos() >= 30)
                .map(Curso::getAlunos)
                .forEach(System.out::println);
        
        
        // Somando a qtde de alunos de cursos com mais de 30 alunos:        
        int soma =
                cursos.stream()
                .filter(c -> c.getAlunos() >= 30)
                .mapToInt(Curso::getAlunos)
                .sum();
        System.out.println(soma);
        
        
        // Imprimindo cursos com mais de 30 alunos e pegando algum aleatóriamente:
        cursos.stream()
                .filter(c -> c.getAlunos() >= 30)
                .findAny()
                //.findFirst()
                .ifPresent(System.out::println);
        
        
        // Salvando o Stream em uma List, já que os métodos do Stream não alteram o dado original:
        List<Curso> cursosFiltrados =
                cursos.stream()
                    .filter(c -> c.getAlunos() >= 30)
                    .collect(Collectors.toList());
        System.out.println(cursosFiltrados);
        
        
        // Salvando o Stream em um Map, já que os métodos do Stream não alteram o dado original:
        cursos.stream()
                .filter(c -> c.getAlunos() >= 30)
                .collect(Collectors.toMap(
                    c -> c.getNome(),
                    c -> c.getAlunos()))
                .forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos + " alunos."));
        
        
        // Salvando o Stream em um Map, já que os métodos do Stream não alteram o dado original,
        // e executando Threads (paralelismo), para situações com muitos dados:
        cursos.parallelStream()
                .filter(c -> c.getAlunos() >= 30)
                .collect(Collectors.toMap(
                    c -> c.getNome(),
                    c -> c.getAlunos()))
                .forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos + " alunos."));
        

    }
}
