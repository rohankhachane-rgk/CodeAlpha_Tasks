import java.util.*;

public class AIChatBot {
    private Map<String, String> knowledgeBase;
    private List<String> greetings;
    private List<String> farewells;
    private Random random;

    public AIChatBot() {
        knowledgeBase = new HashMap<>();
        greetings = Arrays.asList("Hello!", "Hi there!");
        farewells = Arrays.asList("Goodbye,See you later!");
        random = new Random();

        // Initialize knowledge base with some sample responses
        knowledgeBase.put("how are you", "I'm doing well, thank you for asking!");
        knowledgeBase.put("what is your name", "My name is AIChatbot. Nice to meet you!");
        knowledgeBase.put("what is your age", "I don't have a real age, but I was first released in 2025!");
        knowledgeBase.put("what can you do", "I can answer questions and learn new information. Feel free to ask me anything!");
    }

    public String generateResponse(String input) {
        input = input.toLowerCase().trim();

        // Check for greetings
        if (isGreeting(input)) {
            return greetings.get(random.nextInt(greetings.size()));
        }

        // Check for farewells
        if (isFarewell(input)) {
            return farewells.get(random.nextInt(farewells.size()));
        }

        // Check knowledge base for a response
        for (Map.Entry<String, String> entry : knowledgeBase.entrySet()) {
            if (input.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // If no match found, try to learn
        if (input.contains("is") || input.contains("are")) {
            return learnNewInformation(input);
        }

        return "I'm not sure how to respond to that. Can you rephrase or ask me something else?";
    }

    private boolean isGreeting(String input) {
        return input.matches(".*\\b(hi|hello|hey|greetings)\\b.*");
    }

    private boolean isFarewell(String input) {
        return input.matches(".*\\b(bye|goodbye|see you|farewell)\\b.*");
    }

    private String learnNewInformation(String input) {
        String[] parts = input.split("\\s+(is|are)\\s+", 2);
        if (parts.length == 2) {
            String key = parts[0].trim();
            String value = parts[1].trim();
            knowledgeBase.put(key, value);
            return "Thank you for teaching me that " + key + " are " + value + ". I'll remember that!";
        }
        return "I couldn't understand that. Can you please rephrase?";
    }

    public static void main(String[] args) {
        AIChatBot chatbot = new AIChatBot();
        Scanner scanner = new Scanner(System.in);

        System.out.println("AI Chatbot: Hello! How can I assist you today? (Type 'exit' to end the conversation)");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("AI ChatBot: Goodbye! Have a great day!");
                break;
            }

            String response = chatbot.generateResponse(input);
            System.out.println("AI ChatBot: " + response);
        }

        scanner.close();
    }
}