// $(document).ready(() => {
//     if (localStorage.getItem("user.type") != "admin") {
//         window.location.href = "index.html";
//     }
// })

$(document).ready(function () {
  $("#productTable").DataTable({
    ajax: {
      url: "http://localhost:8080/product",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, HEAD",
        "Access-Control-Allow-Headers":
          "Content-Type, Authorization, Accept, X-Requested-With, remember-me",
      },
      dataSrc: "",
    },
    columns: [
      { data: "titel" },
      {
        data: "description",
        render: function (data, type, row, meta) {
          return data.length > 20 ? data.substr(0, 20) + "..." : data;
        },
      },
      {
        data: "imagePath",
        render: function (data, type, row, meta) {
          return (
            '<img src="' +
            data +
            '" width="48" height="48" class="d-block mx-auto" />'
          );
        },
      },
      { data: "price" },
      { data: "category.name" },
      { data: "quantity" },
      {
        data: "id",
        render: function (data, type, row, meta) {
          return (
            '<button class="btn btn-primary m-1" onclick="editProduct(' +
            data +
            ')">Edit</button>' +
            '<button class="btn btn-danger" onclick="deleteProduct(' +
            data +
            ')">Delete</button>'
          );
        },
      },
    ],
  });
});

$(document).ready(function () {
  $("#userTable").DataTable({
    ajax: {
      url: "http://127.0.0.1:8080/admin/user/",
      headers: {
        Authorization: sessionStorage.getItem("token"),
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, HEAD",
        "Access-Control-Allow-Headers":
          "Content-Type, Authorization, Accept, X-Requested-With, remember-me",
      },
      dataSrc: function (json) {
        return json;
      },
      error: function (xhr, error, thrown) {
        $("#userTable").DataTable().draw(false);
      },
    },
    columns: [
      { data: "firstname" },
      { data: "lastname" },
      { data: "username" },
      { data: "email" },
      { data: "userType" },
      { data: "enabled" },
      {
        data: "id",
        render: function (data, type, row, meta) {
          return (
            '<button class="btn btn-primary m-1" onclick="editUser(' +
            data +
            ')">Edit</button>' +
            '<button class="btn btn-danger" onclick="deleteUser(' +
            data +
            ')">Delete</button>'
          );
        },
      },
    ],
    error: function () {
      // Handle DataTables specific error scenario
      console.log("DataTables Error");
    },
  });
});
