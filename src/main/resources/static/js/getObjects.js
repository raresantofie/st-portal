$( document ).ready(function() {

    $("#book-table").css('display','block');
    renderBookTable(0, 10);

});

$( document ).ready(function() {

});

$( document).on('click', '#href-course', function (e) {
    e.preventDefault();
    console.log('now-click');
    renderCourses();
});

function renderBookTable(pageCount, pageSize) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/library/book?pageCount=${pageCount}&pageSize=${pageSize}`,
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            var content = data.content;
            var html = [];
            content.forEach(function (entry) {
                var htmlElement = `<tr class="book-details" data-id="${entry.bookId}">
                <th scope="row">${entry.title}</th>
                    <td>${entry.author}</td>
                    <td>${entry.domain}</td>
                    <td>${entry.publisher}</td>
                </tr>`

                html.push(htmlElement);

            });

            $("#book-list").html(html.join(""));
            if(data.totalPages>1){
                renderPagination(data.totalPages,"book");
            }
        }
    });
}

function renderPageRequestTable(pageCount, pageSize) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/library/paper?pageCount=${pageCount}&pageSize=${pageSize}`,
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            var content = data.content;
            var html = [];
            content.forEach(function (entry) {
                var htmlElement = `<tr class="page-request-details" data-id="${entry.id}">
                <th scope="row" >${entry.date}</th>
                    <td style="word-wrap:  break-word;">${entry.description}</td>
                    <td>${entry.studentName}</td>
                    <td>${entry.studentYear}</td>
                    <td>${entry.status}</td>
                    <td>${!entry.status ? `<button class="btn btn-secondary margin page-request-process" data-id="${entry.id}" type="button"><span class="fa fa-tag"></span>Proceseaza</button>` : "-"}</td>
                </tr>`

                html.push(htmlElement);

            });

            $("#request-paper-list").html(html.join(""));
            if(data.totalPages>1){
                renderPagination(data.totalPages, "paper");
            }
        }
    });
}

function renderPagination(totalPages, context) {

    $("#pagination-div").html("");

    var htmlElemnt = [];
    var htmlHeader = `
           <div class="card-block btn-margins">
            <div class="btn-group">`

    var htmlBody = "";
      for(var i=0; i<totalPages;i++) {
        htmlBody = htmlBody + `<button type="button" class="btn btn-secondary pagination" data-context="${context}" data-page=${i}>${i+1}</button>`
      }

    var htmlFooter = `</div></div>`;

    htmlElemnt.push(htmlHeader, htmlBody, htmlFooter);

    $("#pagination-div").html(htmlElemnt.join(""));

}

function renderBookTableStatusList(id) {

    $("#book-table-details").css('display','block');
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/library/book/entries/${id}`,
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            var html = [];
            data.forEach(function (entry) {
                var htmlElement = `<tr class="book-status-details" data-id="${entry.id}">
                <th scope="row">${entry.isbn}</th>
                    <td>${entry.status ? "IMPRUMUTATA" : "IN STOC"}</td>
                    <td>${entry.rentedBy != null ? entry.rentedBy : "-"}</td>
                </tr>`

                html.push(htmlElement);

            });

            $("#book-table").css("display","none");
            $("#book-details-list").html(html.join(""));
        }
    });
}

function renderCourses() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/student/course`,
        success: function (data) {
            var htmlObj = [];
            data.forEach(function (value) {
               var html = `<div class="col-lg-6 mb-4">
								<div class="card text-center bg-default">
									<div class="card-header">
										<ul class="nav nav-tabs card-header-tabs">
										</ul>
									</div>
									<div class="card-block">
										<h4 class="card-title">An studiu: ${value.studyYear} | Numar locuri : ${value.capacity}</h4>
										<p class="card-text">${value.description}</p>
										<p class="card-text">Sustinut de ${value.maintainedBy}</p>
							            <a href="#" class="btn btn-md btn-primary course-btn"  data-id="${value.id}">Aplica</a></div>
								</div>
							</div>`;
               htmlObj.push(html);
            });

            $("#course-list").html(htmlObj.join(""));
        }
    });
}

function renderTableCourse() {

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/admin/course`,
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            var html = [];
            data.forEach(function (entry) {
                var htmlElement = `<tr class="course-details" data-id="${entry.id}">
                <th scope="row">${entry.description}</th>
                    <td>${entry.count}</td>
                    <td>${entry.faculty}</td>
                </tr>`

                html.push(htmlElement);

            });
            $("#course-list").html(html);
        }
    });
}
$(document).on('click', '.pagination', function(e) {
    e.preventDefault();
    var page = parseInt($(this).data("page"));
    var context = $(this).data("context");
    if(context === "book"){
        renderBookTable(page,2);
    }
    if(context === "paper"){
        renderPageRequestTable(page,2);
    }
});

$(document).on('click', '.book-details', function(e) {
    e.preventDefault();
    var id = parseInt($(this).data("id"));
    renderBookTableStatusList(id);
});

$(document).on('click', '.course-details', function (e) {
    e.preventDefault();
    var id = parseInt($(this).data("id"));
    renderParticipantList(id);
});

function renderParticipantList(id) {
    $('#f-2').addClass('spa-form');
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/admin/course/${id}/entries`,
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {
            var html = [];
            data.forEach(function (entry) {
                var htmlElement = `<tr class="participant-details" data-course=${id} data-student="${entry.studentDto.id}">
                <th scope="row">${entry.studentDto.name.firstName + " " + entry.studentDto.name.lastName}</th>
                    <td>${entry.studentDto.yearOfStudy}</td>
                    <td>${entry.studentDto.yearOfStudy}</td>
                    <td><button type="button" class="btn btn-secondary accept-stud"  data-course=${id} data-student="${entry.studentDto.id}">Accepta</button></td>
                    <td><button type="button" class="btn btn-secondary reject-stud"  data-course=${id} data-student="${entry.studentDto.id}">Respinge</button></td>
                    <td id="select"></td>
                </tr>`

                html.push(htmlElement);

            });
            $("#participant-list").html(html);
        }
    });
}

$(document).on('click', '.course-details', function (e) {
    e.preventDefault();
    var id = parseInt($(this).data("id"));
    renderParticipantList(id);
});

$(document).on('click', '.accept-stud', function (e) {
    e.preventDefault();
    var courseId = parseInt($(this).data("course"));
    var student = parseInt($(this).data("student"));
    $.ajax({
        type: "PUT",
        url: `http://localhost:8080/admin/course/entries?studentId=${student}&courseId=${courseId}&status=ACCEPTED`,
        contentType: 'application/json',
        success: function (data) {
            if(data==="OK"){
                renderBookTableStatusList(id);
                $("#modal-message").text("Procesarea a avut loc cu succes");
                $("#modal-info").modal('show');
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
});

$(document).on('click', '.reject-stud', function (e) {
    e.preventDefault();
    var courseId = parseInt($(this).data("course"));
    var student = parseInt($(this).data("student"));
    $.ajax({
        type: "PUT",
        url: `http://localhost:8080/admin/course/entries?studentId=${student}&courseId=${courseId}&type=REJECTED`,
        contentType: 'application/json',
        success: function (data) {
            if(data==="OK"){
                renderBookTableStatusList(id);
                $("#modal-message").text("Procesarea a avut loc cu succes");
                $("#modal-info").modal('show');
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
});