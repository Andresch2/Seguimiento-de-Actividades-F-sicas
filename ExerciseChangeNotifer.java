interface ExerciseChangeNotifier {
    void onExerciseChanged(String userName, Exercise exercise);
}

class UserExerciseChangeNotifier implements ExerciseChangeNotifier {
    private String userName;

    public UserExerciseChangeNotifier(String userName) {
        this.userName = userName;
    }

    @Override
    public void onExerciseChanged(String userName, Exercise exercise) {
        if (this.userName.equals(userName)) {
            System.out.println("Notificación para " + userName + ": Nuevo ejercicio programado - Tipo: " + exercise.getType() + ", Día: " + exercise.getDay() + ", Tiempo: " + exercise.getTime());
        }
    }
}


