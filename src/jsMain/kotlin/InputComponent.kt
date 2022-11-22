import csstype.ClassName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLFormElement
import react.*
import org.w3c.dom.HTMLInputElement
import react.dom.events.ChangeEventHandler
import react.dom.events.FormEventHandler
import react.dom.html.ButtonType
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.i
import react.dom.html.ReactHTML.button

external interface InputProps : Props {
    var onSubmit: (String) -> Unit
}
external interface EditProps : Props {
    var onSubmit: (String) -> Unit
    var listItem:(ShoppingListItem)

}

val inputComponent = FC<InputProps> { props ->
    val (text, setText) = useState("")

    val submitHandler: FormEventHandler<HTMLFormElement> = {
        it.preventDefault()
        setText("")
        props.onSubmit(text)
    }

    val changeHandler: ChangeEventHandler<HTMLInputElement> = {
        setText(it.target.value)
    }

    form {
        id = "input-form"
        onSubmit = submitHandler
        input {
            type = InputType.text
            onChange = changeHandler
            value = text
        }
    }
}
private val scope = MainScope()

val editComponent = FC<EditProps> { props ->
    val (text, setText) = useState(props.listItem.desc+"!".repeat(props.listItem.priority))

    val submitHandler: FormEventHandler<HTMLFormElement> = {
        it.preventDefault()
        setText("")
        props.onSubmit(text)
    }

    val changeHandler: ChangeEventHandler<HTMLInputElement> = {
        setText(it.target.value)
    }
    form {
        id = "edit-form"
        onSubmit = submitHandler
        button {
            type = ButtonType.submit
            i {
                className = ClassName("fa fa-floppy-o")
            }
        }
        input {
            type = InputType.text
            onChange = changeHandler
            value = text
        }
    }
}