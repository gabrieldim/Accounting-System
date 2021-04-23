async function getFirstNameAndLastName(){
    const response = await fetch("http://localhost:8080/api/user");
    const data = await response.json();

    const element = document.getElementById("nameAndLastName");

    element.innerHTML =data.first_name + " " + data.last_name;

}
getFirstNameAndLastName();