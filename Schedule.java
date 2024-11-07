import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule extends ExerciseNotifier {
    private static Schedule scheduleManager;
    private Map<String, User> users;
    private Map<User, List<Exercise>> userExercisesMap;

    private Schedule() {
        this.users = new HashMap<>();
        this.userExercisesMap = new HashMap<>();
    }

    public static Schedule getScheduleManager() {
        if (scheduleManager == null) {
            scheduleManager = new Schedule();
        }
        return scheduleManager;
    }

    public void registerUser(String userName) {
        if (!users.containsKey(userName)) {
            users.put(userName, new User(userName));
        } else {
            System.out.println("El usuario ya existe!");
        }
    }

    public User getUser(String userName) {
        return users.get(userName);
    }

    public void addExercise(User user, Exercise exercise) {
        if (user != null) {
            List<Exercise> exercises = userExercisesMap.getOrDefault(user, new ArrayList<>());
            boolean exerciseExists = false;
            for (Exercise existingExercise : exercises) {
                if (existingExercise.getType().equals(exercise.getType()) &&
                    existingExercise.getDay().equals(exercise.getDay()) &&
                    existingExercise.getTime().equals(exercise.getTime())) {
                    exerciseExists = true;
                    break;
                }
            }
            if (exerciseExists) {
                System.out.println("¡Ejercicio ya fue programado para este usuario en este momento!");
            } else {
                exercises.add(exercise);
                userExercisesMap.put(user, exercises);
                System.out.println("¡Ejercicio programado con éxito!");
                notifyNotifiers(user.getName(), exercise);
            }
        } else {
            System.out.println("¡Usuario no encontrado!");
        }
    }

    public void modifyExercise(User user, Exercise oldExercise, Exercise newExercise) {
        if (user != null) {
            List<Exercise> exercises = userExercisesMap.get(user);
            if (exercises != null && exercises.remove(oldExercise)) {
                exercises.add(newExercise);
                System.out.println("¡Ejercicio modificado con éxito!");
                notifyNotifiers(user.getName(), newExercise);
            } else {
                System.out.println("¡Ejercicio no encontrado!");
            }
        } else {
            System.out.println("¡Usuario no encontrado!");
        }
    }

    public void viewUsersAndExercises() {
        System.out.println("Los usuarios y sus ejercicios:");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            System.out.println("Usuario: " + entry.getKey());
            System.out.println("Ejercicios:");
            List<Exercise> exercises = userExercisesMap.getOrDefault(entry.getValue(), new ArrayList<>());
            for (Exercise exercise : exercises) {
                System.out.println("-Tipo: " + exercise.getType() + ", Dia: " + exercise.getDay() + ", Hora: " + exercise.getTime());
            }
        }
    }
}
