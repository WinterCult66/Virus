//
$(document).ready(function () {
    $(document).ready(function () {
//           $('#employee_table').DataTable();

        $('#employee_table').DataTable({
            "pageLength": 5,
////            "paging": true,
            "ordering": false,
            "info": false,
            "searching": false,
           "lengthChange": false
//       
            
        });
    });
});

