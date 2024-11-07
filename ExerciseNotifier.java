import java.util.ArrayList;
import java.util.List;

class ExerciseNotifier {
    private List<ExerciseChangeNotifier> notifiers = new ArrayList<>();

    public void attachNotifier(ExerciseChangeNotifier notifier) {
        notifiers.add(notifier);
    }

    public void notifyNotifiers(String userName, Exercise exercise) {
        for (ExerciseChangeNotifier notifier : notifiers) {
            notifier.onExerciseChanged(userName, exercise);
        }
    }
}