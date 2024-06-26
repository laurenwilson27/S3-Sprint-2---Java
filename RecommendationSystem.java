import java.util.ArrayList;
import java.util.List;

/**
 * A class containing a static methods which will analyze a HealthData instance and offer suggestions based on the information in the HealthData.
 */

public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final int MIN_STEPS = 10000;
    private static final double A1C_PREDIABETES = 5.7;
    private static final double A1C_DIABETES = 6.5;

    // This method is static, so the RecommendationSystem doesn't need to be instantiated

    /**
     * Generates health recommendations based on the given HealthData
     * @param healthData The HealthData to analyze
     * @return A list of all recommendations. Each element in the list is a different recommendation.
     */
    public static List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<String>();

        // Analyze heart rate
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
            recommendations.add("Your heart rate is lower than the recommended range. " +
                "Consider increasing your physical activity to improve your cardiovascular health.");
        }
        else
        if (heartRate > MAX_HEART_RATE) {
            recommendations.add("Your heart rate is higher than the recommended range. " + 
            "If you experience chest pain, shortness of breath, weakness or dizziness, seek immediate medical attention.");
        }

        // Analyze steps
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
            recommendations.add("You're not reaching the recommended daily step count. " +
            "Try to incorporate more walking or other physical activities into your daily routine.");
        }

        // Analyze A1C
        double a1c = healthData.getA1C();
        if (a1c >= A1C_DIABETES) {
            recommendations.add("Your A1C indicates that you are diabetic. " +
            "Be sure to see a diabetes specialist regularly, and take your insulin as prescribed.");
        } else
        if (a1c >= A1C_PREDIABETES) {
            recommendations.add("Your A1C indicates that you are prediabetic. " +
            "Speak with your doctor about this. Appropriate medication and diet can reduce your risk of becoming diabetic.");
        }

        return recommendations;
    }
}
