package classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        cursos.stream().filter(c -> c.getAlunos() >= 30).forEach(c -> System.out.println(c.getNome()));
        // 2) usando Method Reference
        Stream cursos30 = cursos.stream().filter(c -> c.getAlunos() >= 30);        
        cursos30.forEach(System.out::println);
        // OU
        cursos.stream().filter(c -> c.getAlunos() >= 30).forEach(System.out::println);
        
    }
}
