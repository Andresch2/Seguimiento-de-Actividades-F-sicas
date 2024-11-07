import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    private static final Pattern DAY_PATTERN = Pattern.compile("^(Lunes|Martes|Miércoles|Jueves|Viernes|Sábado|Domingo)$");
    private static final Pattern TYPE_PATTERN = Pattern.compile("^[a-zA-Z ]+$");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Schedule schedule = Schedule.getScheduleManager();

        while (true) {
            System.out.println("1. Registrarse");
            System.out.println("2. Programar ejercicio");
            System.out.println("3. Modificar ejercicio");
            System.out.println("4. Ver usuarios y ejercicios");
            System.out.println("5. Salir");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Ingresar Nombre: ");
                String userName = scanner.nextLine();
                schedule.registerUser(userName);
            } else if (choice == 2) {
                programarEjercicio(scanner, schedule);
            } else if (choice == 3) {
                modificarEjercicio(scanner, schedule);
            } else if (choice == 4) {
                schedule.viewUsersAndExercises();
            } else if (choice == 5) {
                break;
            }
        }

        scanner.close();
    }

    private static void programarEjercicio(Scanner scanner, Schedule schedule) {
        System.out.print("Ingresar Nombre: ");
        String userName = scanner.nextLine();
        User user = schedule.getUser(userName);
        if (user != null) {
            System.out.print("Introduce el tipo de ejercicio: ");
            String type = scanner.nextLine();
            if (!validateInput(type, TYPE_PATTERN)) {
                System.out.println("Tipo de ejercicio inválido.");
                return;
            }
            System.out.print("Introduzca el día: ");
            String day = scanner.nextLine();
            if (!validateInput(day, DAY_PATTERN)) {
                System.out.println("Día inválido.");
                return;
            }
            System.out.print("Introduzca la hora (HH:MM): ");
            String time = scanner.nextLine();
            if (!validateInput(time, TIME_PATTERN)) {
                System.out.println("Hora inválida.");
                return;
            }
            Exercise exercise = new Exercise(type, day, time);
            schedule.addExercise(user, exercise);
        } else {
            System.out.println("¡Usuario no encontrado!");
        }
    }

    private static void modificarEjercicio(Scanner scanner, Schedule schedule) {
        System.out.print("Ingresar Nombre: ");
        String userName = scanner.nextLine();
        User user = schedule.getUser(userName);
        if (user != null) {
            System.out.print("Introduce el tipo de ejercicio a modificar: ");
            String oldType = scanner.nextLine();
            System.out.print("Introduzca el día del ejercicio a modificar: ");
            String oldDay = scanner.nextLine();
            System.out.print("Introduzca la hora del ejercicio a modificar (HH:MM): ");
            String oldTime = scanner.nextLine();

            if (!validateInput(oldType, TYPE_PATTERN) || !validateInput(oldDay, DAY_PATTERN) || !validateInput(oldTime, TIME_PATTERN)) {
                System.out.println("Datos de ejercicio a modificar inválidos.");
                return;
            }

            Exercise oldExercise = new Exercise(oldType, oldDay, oldTime);

            System.out.print("Introduce el nuevo tipo de ejercicio: ");
            String newType = scanner.nextLine();
            if (!validateInput(newType, TYPE_PATTERN)) {
                System.out.println("Tipo de ejercicio inválido.");
                return;
            }
            System.out.print("Introduzca el nuevo día: ");
            String newDay = scanner.nextLine();
            if (!validateInput(newDay, DAY_PATTERN)) {
                System.out.println("Día inválido.");
                return;
            }
            System.out.print("Introduzca la nueva hora (HH:MM): ");
            String newTime = scanner.nextLine();
            if (!validateInput(newTime, TIME_PATTERN)) {
                System.out.println("Hora inválida.");
                return;
            }

            Exercise newExercise = new Exercise(newType, newDay, newTime);
            schedule.modifyExercise(user, oldExercise, newExercise);
        } else {
            System.out.println("¡Usuario no encontrado!");
        }
    }

    private static boolean validateInput(String input, Pattern pattern) {
        return pattern.matcher(input).matches();
    }
}
