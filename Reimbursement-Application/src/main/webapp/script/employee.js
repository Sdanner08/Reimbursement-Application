/* gets id from query param */
const urlParams = new URLSearchParams(window.location.search)
const userId = urlParams.get("id")
const roleId = urlParams.get("roleId")


//respondes to the tab clicks to show which page you want
function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
  }

// On load to get requests
  window.onload = async function(){
    const sessionRes = await fetch(`${domain}/api/check-session`)
    const sessionUserData = await sessionRes.json();
    let sessionInfo = sessionUserData.data;
  
    //validates session
     if(sessionInfo){
        if(sessionInfo.userId != userId){
          window.location = `${domain}/`;
        }
      }
      if(sessionInfo.userRoleIdFk != 1){
        window.location = `${domain}/`;
      }
      showMyReimbursments();       
  }

  //Pulls all the information for employee
  //It will then filter it based on the status id
  //It will then appended it to the approperate tab
async function showMyReimbursments(){
    //const listResponse = await fetch(`${domain}/api/reimbursement?userId=${userId}`)
    const listResponse = await fetch(`${domain}/api/allReimbur?userId=${userId}`)
    const listData = await listResponse.json();


    //create parent elements
    let reimburListElem = document.getElementById("reimburList");
    let approvedListElem = document.getElementById("approvedList");
    let deniedListElem = document.getElementById("deniedList");
    let individualRimburElem = document.createElement("div") 
    listData.data.forEach(reimbur =>{
       

        //if statements to sort the reimbursments 
        if(reimbur.reimburStatusFk == 1){
           let status = "Pending"
            
        let listOfReimbElem = document.createElement("div")
        listOfReimbElem.className = "list-of-reimb";

        let descriptElem = document.createElement("div")
        descriptElem.className = "description"
        descriptElem.innerHTML = reimbur.reimburDescription;

        let amountElem = document.createElement("div")
        var conVertDisp = (reimbur.reimburAmount / 100);
        amountElem.className = "amount"
        amountElem.innerHTML = conVertDisp;

        let statusElem = document.createElement("div")
        statusElem.className = "status"
        statusElem.innerHTML = status;

        let viewBtnElem = document.createElement("button");
        viewBtnElem.innerText = "View Item";
        viewBtnElem.className = "btn btn-primary";
        viewBtnElem.id = "view-reimbur";

        listOfReimbElem.appendChild(descriptElem);
        listOfReimbElem.appendChild(amountElem);
        listOfReimbElem.appendChild(statusElem);
        listOfReimbElem.appendChild(viewBtnElem);

        individualRimburElem.appendChild(listOfReimbElem)
        reimburListElem.appendChild(individualRimburElem);

        viewBtnElem.onclick = function(){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
        }
    }
        if(reimbur.reimburStatusFk == 2){
        let status = "Approved"

        let listOfReimbElem = document.createElement("div")
        listOfReimbElem.className = "list-of-reimb";

        let descriptElem = document.createElement("div")
        descriptElem.className = "description"
        descriptElem.innerHTML = reimbur.reimburDescription;

        let amountElem = document.createElement("div")
        var conVertDisp = (reimbur.reimburAmount / 100);
        amountElem.className = "amount"
        amountElem.innerHTML = conVertDisp;

        let statusElem = document.createElement("div")
        statusElem.className = "status"
        statusElem.innerHTML = status;

        let viewBtnElem = document.createElement("button");
        viewBtnElem.innerText = "View Item";
        viewBtnElem.className = "btn btn-primary";
        viewBtnElem.id = "view-reimbur";

        listOfReimbElem.appendChild(descriptElem);
        listOfReimbElem.appendChild(amountElem);
        listOfReimbElem.appendChild(statusElem);
        listOfReimbElem.appendChild(viewBtnElem);

        approvedListElem.appendChild(listOfReimbElem);

        viewBtnElem.onclick = function(){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
        }
    }
    
    if(reimbur.reimburStatusFk == 3){
        let status = "Denied"

    let listOfReimbElem = document.createElement("div")
    listOfReimbElem.className = "list-of-reimb";

    let descriptElem = document.createElement("div")
    descriptElem.className = "description"
    descriptElem.innerHTML = reimbur.reimburDescription;

    let amountElem = document.createElement("div")
    var conVertDisp = (reimbur.reimburAmount / 100);
    amountElem.className = "amount"
    amountElem.innerHTML = conVertDisp;

    let statusElem = document.createElement("div")
    statusElem.className = "status"
    statusElem.innerHTML = status;

    let viewBtnElem = document.createElement("button");
    viewBtnElem.innerText = "View Item";
    viewBtnElem.className = "btn btn-primary";
    viewBtnElem.id = "view-reimbur";

    listOfReimbElem.appendChild(descriptElem);
    listOfReimbElem.appendChild(amountElem);
    listOfReimbElem.appendChild(statusElem);
    listOfReimbElem.appendChild(viewBtnElem);

    deniedListElem.appendChild(listOfReimbElem);

    viewBtnElem.onclick = function(){
        window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`
    }
    }
    })
}

//gets form element
let newRimbursmentForm = document.getElementById("newReimbur")

//on submit of form it will execute this function
newRimbursmentForm.onsubmit = async function(e){
    e.preventDefault();

    //collects information
    let newReimburDesri = document.getElementById("newDescription").value;
    let newReimburAmount = document.getElementById("newAmount").value;
    var select = document.getElementById("inputGroupSelect01");
    var value = select.options[select.selectedIndex].value;
    var conVert = newReimburAmount *100;

    //posts it to back end
    let createNew = await fetch(`${domain}/api/reimbursement`,{
        method: "POST",
        body: JSON.stringify({
            reimburAmount: conVert,
            reimburDescription: newReimburDesri,
            reimburAuthorFk: userId,
            reimburTypeFk: value
        })
    })
    let createReimbur = await createNew.json()
    if(createReimbur.success){
        window.location = `${domain}/employee?id=${userId}`
    }
 } 

 //log out button ends session
 let logoutBtn = document.getElementById("logout-btn")
 logoutBtn.onclick = async function(){
     let logoutResult = await fetch(`${domain}/api/logout`)

     let logoutData = await logoutResult.json();

     if(logoutData.success)
     window.location = `${domain}/`;
 }