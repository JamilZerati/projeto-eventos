import {Header} from "./components/header/Header.tsx";
import './App.css';
import {Main} from "./components/main/Main.tsx";

function App() {

    return (
        <div className={"flex-col gap-8 m-4"}>
                <Header/>
                <Main/>
        </div>
    )
}

export default App
