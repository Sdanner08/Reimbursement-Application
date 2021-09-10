/* //gets id from query param */
const urlParams = new URLSearchParams(window.location.search)
const reimburId = urlParams.get("reimburId")
const userId = urlParams.get("userId")
const roleId = urlParams.get("roleId")

//checks session
window.onload = async function(){
    const sessionRes = await fetch(`${domain}/api/check-session`)
    const sessionUserData = await sessionRes.json();
    let sessionInfo = sessionUserData.data;

     if(sessionInfo){
        if(sessionInfo.userId != userId){
          window.location = `${domain}/`;
        }
      }
      if(sessionInfo.userRoleIdFk != roleId){
        window.location = `${domain}/`;
      }
      showOneReimbursement();  
      
  }

  //gets info for 1 reimbursment based on ID
async function showOneReimbursement(){
    const listResponse = await fetch(`${domain}/api/oneReimbur?reimburId=${reimburId}`)
    const listData = await listResponse.json();

    const employeeResponse = await fetch(`${domain}/api/getAllUsers`)
    const employeeData = await employeeResponse.json();

    //create elements 
    let reimbur = listData.data[0];
    let fullDetailElem = document.getElementById("fullDetail")
    let reimburIdElemM = document.getElementById("reimbursmentIdLoc")
    let reimburAuthorElemM = document.getElementById("reimbursmentAuthorLoc")
    let reimburResolverElemM = document.getElementById("reimbursmentResolverLoc")
    let reimburDateElemM = document.getElementById("reimbursmentDateLoc")
    let reimburDecElemM = document.getElementById("reimbursmentDecripLoc")
    let reimburAmountElemM = document.getElementById("reimbursmentAmountLoc")
    let reimburStatElemM = document.getElementById("reimbursmentStatLoc")

    //Name Variables
    let nameAuthor;
    let nameResolver;

    //Getting Author's name
    employeeData.data.forEach(UserRId =>{
        if(UserRId.userId === reimbur.reimburAuthorFk){
        nameAuthor = UserRId.firstName;
        }

    })

    //Getting Resolver's name
    employeeData.data.forEach(ResolverId =>{

        if(ResolverId.userId === reimbur.reimburResolverFk){
        nameResolver = ResolverId.firstName;
        }
        if(reimbur.reimburResolverFk == 0){
            nameResolver = "Still Pending"
        }

    })

    //status filter
    let status;
    if(reimbur.reimburStatusFk == 1){
        status = "Pending"
    }
    if(reimbur.reimburStatusFk == 2){
        status = "Approved"
    }
    if(reimbur.reimburStatusFk == 3){
        status = "Denied"
    }

    //createse elements and places their value
    let listOfReimbElem = document.createElement("div")
    listOfReimbElem.className = "list-of-reimb";

    let reimbIdElem = document.createElement("div")
    reimbIdElem.innerHTML = reimbur.reimburId;

    let reimbAuthorElem = document.createElement("div")
    reimbAuthorElem.innerHTML = nameAuthor;

    let reimbResolverElem = document.createElement("div")
    reimbResolverElem.innerHTML = nameResolver;

    let reimbDateElem = document.createElement("div")
    reimbDateElem.innerHTML = reimbur.reimburSubmitted;

    let descriptElem = document.createElement("div")
    descriptElem.innerHTML = reimbur.reimburDescription;

    let amountElem = document.createElement("div")
    var conVertDisp = (reimbur.reimburAmount / 100);
    amountElem.innerHTML = conVertDisp;
    
    let statusElem = document.createElement("div")
    statusElem.innerHTML = status;

    let approveBtn = document.createElement("button")
    approveBtn.innerHTML = "Approve";
    approveBtn.className = "btn btn-primary"
    approveBtn.id = "approve-btn";

    let deniedBtn = document.createElement("button")
    deniedBtn.innerHTML = "Deny";
    deniedBtn.className = "btn btn-primary"
    deniedBtn.id = "denied-btn";

    //places buttons based on if its pending then by if its financial manager
    if(status === "Pending"){
     if(roleId == 2){
        listOfReimbElem.appendChild(approveBtn);
        listOfReimbElem.appendChild(deniedBtn);  
    }
}
    //puts all the info on the DOM
    reimburIdElemM.appendChild(reimbIdElem);
    reimburAuthorElemM.appendChild(reimbAuthorElem)
    reimburResolverElemM.appendChild(reimbResolverElem)
    reimburDateElemM.appendChild(reimbDateElem);
    reimburDecElemM.appendChild(descriptElem);
    reimburAmountElemM.appendChild(amountElem);
    reimburStatElemM.appendChild(statusElem);

    fullDetailElem.appendChild(listOfReimbElem);

    //Approved button function
    let approveBtnId = document.getElementById("approve-btn")
    approveBtnId.onclick = async function(){
        let response = await fetch(`${domain}/api/finance/reimbursement`,{
            method: "PATCH",
            body: JSON.stringify({
                reimburId: reimburId,
                reimburResolverFk: userId,
                reimburStatusFk: 2
            })
        })
            let responseData = await response.json();
            //had to reload the window as it stopped working for if statement
           // if(responseData.success){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`

         //   }
        } 
    //Denied button function
    let deniedBtnId = document.getElementById("denied-btn")
    deniedBtnId.onclick = async function(){
        let response = await fetch(`${domain}/api/finance/reimbursement`,{
            method: "PATCH",
            body: JSON.stringify({
                reimburId: reimburId,
                reimburResolverFk: userId,
                reimburStatusFk: 3
            })
        })
            let responseData = await response.json();
            //had to reload the window as it stopped working for if statement
           // if(responseData.success){
            window.location = `${domain}/reimbursment?reimburId=${reimbur.reimburId}&userId=${userId}&roleId=${roleId}`

         //   }
        } 
}

//back button function
//does a check for roleID to send to the appropriate window location
let backBtn = document.getElementById("back-btn");
backBtn.onclick = function(){
    if(roleId ==2){
        window.location = `${domain}/financeManager?id=${userId}&roleId=${roleId}`
    }
    if(roleId ==1)
    window.location = `${domain}/employee?id=${userId}&roleId=${roleId}`
}