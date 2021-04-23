async function getFirstNameAndLastName(){
    const response = await fetch("http://localhost:8080/api/user");
    const data = await response.json();

    const element = document.getElementById("nameAndLastName");

    element.innerHTML =data.first_name + " " + data.last_name;

}

async function getEarnings() {
    const response = await fetch("http://localhost:8080/api/dashboard/earnings");
    const data = await response.json();
    if(data.earnings===undefined){
        document.getElementById("earnings").innerHTML = '$' + 0;
    }else{
    document.getElementById("earnings").innerHTML = '$' + data.earnings;
    }
}

async function getEarningsAnnual() {
    const response = await fetch("http://localhost:8080/api/dashboard/earningsYearly");
    const data = await response.json();
    if(data.earnings===undefined){
        document.getElementById("annually").innerHTML = '$' + 0;
    }else{
        document.getElementById("annually").innerHTML = '$' + data.earnings;
    }
}

getEarnings();
getEarningsAnnual();
getFirstNameAndLastName();