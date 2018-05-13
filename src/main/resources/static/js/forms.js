var hostname="http://localhost:8080"

$('#add-university-form').on('click', function() {
	resetLayout();
	$('#university-form').removeClass('spa-form');
});

$('#library-permit').on('click', function() {
    resetLayout();
    $('#request-permit-form').removeClass('spa-form');
});

$('#adeverinta-student').on('click', function() {
    resetLayout();
    $('#request-adeverinta-form').removeClass('spa-form');
});

$('#adeverinta-calatorie').on('click', function() {
    resetLayout();
    $('#request-calatorie-form').removeClass('spa-form');
});

$('#carnet-note').on('click', function() {
    resetLayout();
    $('#request-carnet-form').removeClass('spa-form');
});

$('#add-student-form').on('click', function() {
	resetLayout();
	$('#student-form').removeClass('spa-form');
	renderFacultyDropdown("#faculty-dropdown");
});


$('#add-librarian-form').on('click', function() {
	resetLayout();
	$('#librarian-form').removeClass('spa-form');
	renderFacultyDropdown("#faculty-dropdown-universityEmployee");
});

$('#add-course-form').on('click', function() {
    resetLayout();
    $('#course-form').removeClass('spa-form');
    renderFacultyDropdown("#faculty-dropdown-course");
});

$('#href-course').on('click', function() {
    resetLayout();
    $('#courses').removeClass('spa-form');
    renderFacultyDropdown("#faculty-dropdown-course");
});

$('#see-courses').on('click',function () {
    $('#f-1').addClass('spa-form');
    renderTableCourse();

});
function renderFacultyDropdown(dropdown) {
	$.ajax({
		type: "GET",
		url: hostname+"/admin/faculties/all",
		success: function(response) {
			response.forEach(function(entry) {
				var htmlElement = `<option value="${entry.id}">${entry.name}</option>`;
				$(dropdown).append(htmlElement);
			});
		}

	});
}

function generateIsbnDiv() {
	var id;
	if($("[class*='isbn-class']").length==0){
		id=0;
	} else {
		id = $("[class*='isbn-class']").length+1;
	}
	
	var htmlElement = `<div class="form-group row" id="generate-isbn">
												<label class="col-md-3 col-form-label">ISBN</label>
												<div class="col-md-9">
													<input type="text" name="regular" id="isbn-${id}" class="form-control isbn-value">
												</div>
											</div>`;
											
	$("#isbn-form-div").append(htmlElement);
	
}

$(document).on('click', '#generate-isbn-entry', function(e){
	e.preventDefault();
	generateIsbnDiv();

});
$(document).on('click', '#add-faculty', function(e){
	e.preventDefault();

	var facultyObject = new Object();
	var libraryCreationDto = new Object();
	var secretaryCreationDto = new Object();
	var emailObj = new Object();
	emailObj.email = $("#email-library").val();
	libraryCreationDto.email = emailObj;
	libraryCreationDto.phoneNumber = $("#phone-number-library").val();

    var emailObj1 = new Object();
    emailObj1.email = $("#secretary-library").val();
    secretaryCreationDto.email = emailObj1;
    secretaryCreationDto.phoneNumber = $("#phone-number-secretary").val();
	
	facultyObject.name =  $('#faculty-name').val();
	facultyObject.libraryCreationDto = libraryCreationDto;
    facultyObject.secretaryCreationDto = secretaryCreationDto;

	var endpoint = "/admin/faculties";
	var url = hostname + endpoint;
	
	console.log(url);
	console.log(JSON.stringify(facultyObject));
	
	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify(facultyObject),
		contentType: 'application/json',
		dataType: 'json',
		success: function( response ) {
			$("#modal-message").text("Facultate a fost salvata in baza de date");
			$("#modal-info").modal('show');
		},
		error: function(error){
			var message = error.responseJSON.message;
			if(message==="ELEMENT_ALREADY_PRESENT"){
			$("#modal-message").text("Facultate exista deja");
			$("#modal-info").modal('show');
			} else {
			$("#modal-message").text(message);
			$("#modal-info").modal('show');
			}
		}
	});
});
$(document).on('click', '#add-student', function(e){
	e.preventDefault();

	var student = new Object();
	var email = new Object();
	var name = new Object();
	email.email = $("#student-email").val();
	name.firstName = $("#first-name").val();
	name.lastName = $("#last-name").val();
	
	student.email = email;
	student.name = name;
	
	student.password = $("#password").val();
	student.username = $("#username").val();
	
	student.faculty = $('#faculty-dropdown option:selected').text();
	var gender =  $('#gender-dropdown option:selected').text();
	student.yearOfStudy = parseInt($('#study-year').val());
	student.accommodationStatus = Boolean($('#saccomodation-status').val());
	if(gender=="BARBAT") {
		student.gender="MALE";
	} else {
		student.gender="FEMALE";
	}
	

	var endpoint = "/admin/user/student"
	var url = hostname + endpoint;
	
	console.log(url);
	console.log(JSON.stringify(student));
	
	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify(student),
		contentType: 'application/json',
		dataType: 'json',
		success: function( response ) {
			$("#modal-message").text("Studentul a fost salvata in baza de date");
			$("#modal-info").modal('show');
		},
		error: function(error){
			var message = error.responseJSON.message;
			if(message==="ELEMENT_ALREADY_PRESENT"){
			$("#modal-message").text("Facultate exista deja");
			$("#modal-info").modal('show');
			} else {
			$("#modal-message").text(message);
			$("#modal-info").modal('show');
			}
		}
	});
});
$(document).on('click', '#add-librarian', function(e){
	e.preventDefault();

	var librarian = new Object();
	var email = new Object();
	var name = new Object();
	email.email = $("#librarian-email").val();
	name.firstName = $("#librarian-first-name").val();
	name.lastName = $("#librarian-last-name").val();
	
	librarian.email = email;
	librarian.name = name;
	
	librarian.password = $("#librarian-password").val();
	librarian.username = $("#librarian-username").val();
	
	librarian.faculty = $('#faculty-dropdown-librarian option:selected').text();
	
	var endpoint = "/admin/user/librarian"
	var url = hostname + endpoint;
	
	console.log(url);
	console.log(JSON.stringify(librarian));
	
	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify(librarian),
		contentType: 'application/json',
		dataType: 'json',
		success: function( response ) {
			$("#modal-message").text("Bibliotecarul a fost salvata in baza de date");
			$("#modal-info").modal('show');
		},
		error: function(error){
			var message = error.responseJSON.message;
			if(message==="ELEMENT_ALREADY_PRESENT"){
			$("#modal-message").text("Bibliotecarul exista deja");
			$("#modal-info").modal('show');
			} else {
			$("#modal-message").text(message);
			$("#modal-info").modal('show');
			}
		}
	});
});
$(document).on('click', '#add-book', function(e){
	e.preventDefault();

	var book = new Object();
	book.title = $("#book-name").val();
	book.author = $("#book-author").val();
	book.domain = $("#book-domain").val();
	book.publisher = $("#book-publisher").val();
	
	var bookStatusDtoList = new Array();
	
	$("[class*='isbn-value']").each(function(){
		var bookStatusDto = new Object();
		var str =$(this).val();
		if(str) { 
			bookStatusDto.isbn=str;
			bookStatusDto.status=false;
			bookStatusDtoList.push(bookStatusDto);
		}
	});
	
	book.bookStatusDtoList = bookStatusDtoList;
	
	var endpoint = "/library/book"
	var url = hostname + endpoint;
	
	console.log(url);
	console.log(JSON.stringify(book));
	
	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify(book),
		contentType: 'application/json',
		dataType: 'json',
		xhrFields: {
			withCredentials: true
		},
		success: function( response ) {
			$("#modal-message").text("Cartea a fost salvata in baza de date");
			$("#modal-info").modal('show');
		},
		error: function(error){
			var message = error.responseJSON.message;
			if(message==="ELEMENT_ALREADY_PRESENT"){
			$("#modal-message").text("Cartea exista deja");
			$("#modal-info").modal('show');
			} else {
			$("#modal-message").text(message);
			$("#modal-info").modal('show');
			}
		}
	});
});
$(document).on('click', '#submit-request-paper', function (e) {
	e.preventDefault();
	var paperRequest = {};
	paperRequest.description=$("#request-paper-description").val();
    paperRequest.paperRequestType="LIBRARY_PERMIT";
	$.ajax({
        type: "POST",
        url: "http://localhost:8080/student/paper",
        data: JSON.stringify(paperRequest),
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function( response ) {
            $("#modal-message").text("Cererea a fost trimisa");
            $("#modal-info").modal('show');
        },
        error: function(error){
            var message = error.responseJSON.message;
            if(message==="ELEMENT_ALREADY_PRESENT"){
                $("#modal-message").text("Cartea exista deja");
                $("#modal-info").modal('show');
            } else {
                $("#modal-message").text(message);
                $("#modal-info").modal('show');
            }
        }
    });
});
$(document).on('click', '#submit-adeverinta-paper', function (e) {
    e.preventDefault();
    var paperRequest = {};
    paperRequest.description=$("#request-adeverinta-description").val();
    paperRequest.paperRequestType="STUDENT_PAPER";

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/student/paper",
        data: JSON.stringify(paperRequest),
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function( response ) {
            $("#modal-message").text("Cererea a fost trimisa");
            $("#modal-info").modal('show');
        },
        error: function(error){
            var message = error.responseJSON.message;
            if(message==="ELEMENT_ALREADY_PRESENT"){
                $("#modal-message").text("Cartea exista deja");
                $("#modal-info").modal('show');
            } else {
                $("#modal-message").text(message);
                $("#modal-info").modal('show');
            }
        }
    });

});
$(document).on('click', '#submit-calatorie-paper', function (e) {
    e.preventDefault();
    var paperRequest = {};
    paperRequest.description=$("#request-calatorie-description").val();
    paperRequest.paperRequestType="STUDENT_TRAVEL_ID";

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/student/paper",
        data: JSON.stringify(paperRequest),
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function( response ) {
            $("#modal-message").text("Cererea a fost trimisa");
            $("#modal-info").modal('show');
        },
        error: function(error){
            var message = error.responseJSON.message;
            if(message==="ELEMENT_ALREADY_PRESENT"){
                $("#modal-message").text("Cartea exista deja");
                $("#modal-info").modal('show');
            } else {
                $("#modal-message").text(message);
                $("#modal-info").modal('show');
            }
        }
    });

});
$(document).on('click', '#submit-carnet-paper', function (e) {
    e.preventDefault();
    var paperRequest = {};
    paperRequest.description=$("#request-carnet-description").val();
    paperRequest.paperRequestType="STUDENT_ID";

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/student/paper",
        data: JSON.stringify(paperRequest),
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function( response ) {
            $("#modal-message").text("Cererea a fost trimisa");
            $("#modal-info").modal('show');
        },
        error: function(error){
            var message = error.responseJSON.message;
            if(message==="ELEMENT_ALREADY_PRESENT"){
                $("#modal-message").text("Cartea exista deja");
                $("#modal-info").modal('show');
            } else {
                $("#modal-message").text(message);
                $("#modal-info").modal('show');
            }
        }
    });

});
$(document).on('click', '.page-request-process', function (e) {

    var id = $(this).data('id');
    $.ajax({
        type: "PUT",
        url: `http://localhost:8080/library/paper/${id}`,
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
$(document).on('click', '#add-course',function (e) {
	e.preventDefault();

	var obj = {};
	obj.description = $("#course-description").val();
    obj.studyYear = $("#studyYear").val();
    obj.capacity = $("#capacity").val();
    obj.maintainedBy = $("#maintainedBy").val();
    obj.faculty = $('#faculty-dropdown-course option:selected').text();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/admin/course",
        data: JSON.stringify(obj),
        contentType: 'application/json',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: function( response ) {
            $("#modal-message").text("Cererea a fost trimisa");
            $("#modal-info").modal('show');
        },
        error: function(error){
            var message = error.responseJSON.message;
            if(message==="ELEMENT_ALREADY_PRESENT"){
                $("#modal-message").text("Cartea exista deja");
                $("#modal-info").modal('show');
            } else {
                $("#modal-message").text(message);
                $("#modal-info").modal('show');
            }
        }
    });

});

$(document).on('click', '.course-btn', function (e) {
	e.preventDefault()
	var id = $(this).data('id');
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/student/course?courseId=${id}`,
        contentType: 'application/json',
        success: function (data) {
            console.log(data)
        },
        error: function (data) {
            console.log(data)
        }
    });
});
function resetLayout() {
    $('section[id*="-form"]').each(function () {
        $(this).addClass("spa-form");
    });
}



