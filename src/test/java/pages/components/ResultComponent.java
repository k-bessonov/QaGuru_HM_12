package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultComponent {

    private final SelenideElement verifyResultsWindow = $(".modal-dialog"),
            tableResponsive = $(".table-responsive");

    public void verifyResultsAppears() {
        verifyResultsWindow.should(appear);
    }

    public void checkResult(String key, String value) {
        tableResponsive.$(byText(key)).parent()
                .shouldHave(text(value));
    }

    public void verifyNegativeResultsAppears() {
        verifyResultsWindow.shouldNot(appear);

    }

}