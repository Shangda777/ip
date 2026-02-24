package richal.ai;

import java.util.List;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

/**
 * Provides AI-powered responses for Richal using Google Gemini via langchain4j.
 * Handles both natural language questions about app features and
 * natural language commands that are translated into app commands.
 */
public class AiHelper {

    /**
     * Prefix used in AI responses to indicate a command should be executed.
     * e.g. "COMMAND: todo buy milk"
     */
    public static final String COMMAND_PREFIX = "COMMAND:";

    private static final String SYSTEM_PROMPT =
            "You are an AI assistant for a CLI task management app called Richal.\n\n"
            + "When the user asks a QUESTION about the app (e.g. 'how do I add a task?', "
            + "'what commands are available?'), answer concisely in natural language.\n\n"
            + "When the user makes an ACTION REQUEST (e.g. 'add a task to buy milk', "
            + "'delete task 2', 'show my tasks'), respond with ONLY:\n"
            + "COMMAND: <the exact app command>\n\n"
            + "Available commands:\n"
            + "1. todo <description>\n"
            + "2. deadline <description> /by <date>   (date format: d/M/yyyy HHmm, e.g. 25/2/2026 1800)\n"
            + "3. event <description> /from <date> /to <date>\n"
            + "4. list\n"
            + "5. mark <task_number>\n"
            + "6. unmark <task_number>\n"
            + "7. delete <task_number>\n"
            + "8. find <keyword>\n\n"
            + "Examples:\n"
            + "User: 'add a task to buy groceries by tomorrow'  → COMMAND: deadline buy groceries /by 25/2/2026 0000\n"
            + "User: 'show all tasks'                           → COMMAND: list\n"
            + "User: 'how do I mark a task as done?'           → To mark a task as done, use: mark <task_number>";

    private final ChatLanguageModel model;

    /**
     * Creates an AiHelper using the LLM_API_KEY environment variable.
     */
    public AiHelper() {
        this.model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("LLM_API_KEY"))
                .modelName("gemini-2.5-flash")
                .build();
    }

    /**
     * Sends the user's input to the AI. The AI decides whether to:
     * - Answer a question (returns natural language text), or
     * - Execute a command (returns a string starting with "COMMAND: <app command>").
     *
     * @param userPrompt the user's natural language input
     * @return the AI response — either plain text or "COMMAND: <command>"
     */
    public String getAiResponse(String userPrompt) {
        ChatRequest request = ChatRequest.builder()
                .messages(List.of(
                        SystemMessage.from(SYSTEM_PROMPT),
                        UserMessage.from(userPrompt)
                ))
                .build();
        ChatResponse response = model.chat(request);
        return response.aiMessage().text().trim();
    }
}
