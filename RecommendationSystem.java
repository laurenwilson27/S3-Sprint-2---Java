import java.util.ArrayList;
import java.util.List;

/**
 * In this basic version of the
 * RecommendationSystem class, complete the generateRecommendations to take a
 * HealthData object as input and generates recommendations based on the user's heart rate and step count.
 * You can also expand this class to include more health data analysis and generate more specific
 * recommendations based on the user's unique health profile
 * NOTE:
 * To integrate this class into your application, you'll need to pass the HealthData object to the generateRecommendations method
 * and store the generated recommendations in the recommendations table in the database.
 */

public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final int MIN_STEPS = 10000;
    private static final double A1C_PREDIABETES = 5.7;
    private static final double A1C_DIABETES = 6.5;

    public List<String> generateRecommendations(HealthData healthData) {
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
