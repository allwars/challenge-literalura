package com.challenge.challenge_literatura;

import com.challenge.challenge_literatura.Service.LiteraturaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challenge.challenge_literatura.Model.Autor;
import com.challenge.challenge_literatura.Model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LiteraturaService literaturaService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("------ MENÚ PRINCIPAL ------");
			System.out.println("1 - Buscar Libros por Título");
			System.out.println("2 - Listar Autores Registrados");
			System.out.println("3 - Listar Libros Registrados");
			System.out.println("4 - Listar Autores por Año");
			System.out.println("5 - Listar Libros por Idioma");
			System.out.println("0 - SALIR DEL PROGRAMA");
			System.out.print("Elija una opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine();  // Consume el salto de línea
			switch (opcion) {
				case 1:
					System.out.print("Ingrese el título del libro: ");
					String titulo = scanner.nextLine();
					try {
						List<Libro> libros = literaturaService.buscarLibrosPorTitulo(titulo);
						libros.forEach(libro -> System.out.println(libro.getTitulo()));
					} catch (IOException e) {
						System.err.println("Error al buscar libros: " + e.getMessage());
					}
					break;
				case 2:
					List<Autor> autores = literaturaService.listarAutoresRegistrados();
					autores.forEach(autor -> System.out.println(autor.getNombre()));
					break;
				case 3:
					List<Libro> libros = literaturaService.listarLibrosRegistrados();
					libros.forEach(libro -> System.out.println(libro.getTitulo()));
					break;
				case 4:
					System.out.print("Ingrese el año: ");
					int ano = scanner.nextInt();
					scanner.nextLine();  // Consume el salto de línea
					List<Autor> autoresPorAno = literaturaService.listarAutoresPorAno(ano);
					autoresPorAno.forEach(autor -> System.out.println(autor.getNombre()));
					break;
				case 5:
					System.out.print("Ingrese el idioma: ");
					String idioma = scanner.nextLine();
					List<Libro> librosPorIdioma = literaturaService.listarLibrosPorIdioma(idioma);
					librosPorIdioma.forEach(libro -> System.out.println(libro.getTitulo()));
					break;
				case 0:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción no válida. Intente nuevamente.");
			}
		} while (opcion != 0);
	}
}