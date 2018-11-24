package utils;


public abstract class Dialog {

    public static enum Type {SUCCESS, INFO, ERROR, WARN};

//    /**
//     * A helper function to get a simple uniform dialog on user-action reqest as
//     * a responce. To using this, you need somthing in html and javascript.<br>
//     *
//     * html(use the funktion getDialogSkel):
//     * <pre>
//     * {@code
//     * <div class="modal fade" id="dialogModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
//     *    <div class="modal-dialog modal-dialog-centered" role="document">
//     *      <div class="modal-content" id="modalDialogContent"></div>
//     *    </div>
//     * </div>
//     * }
//     * </pre>
//     * JavaScript (by jquery):
//     * <pre>
//     * {@code
//     * var url = '/dosomething';
//     * $.get(url, function(response) {
//     *   document.getElementById("modalDialogContent").innerHTML = response;
//     *   $('#dialogModal').modal('show')
//     * });
//     * }
//     * </pre>
//     *
//     * @param type The type of the dialog as Dialog.Type.
//     * @param title The title of the dialog.
//     * @param message The message of the dialog.
//     * @return Returns a Html-Sniped for the bootstrap-modal-dialog.
//     */
//    public static String getDialog(Dialog.Type type, String title, String message) {
//        String ret = "";
//
//        switch (type) {
//            case SUCCESS:
//                ret = "<div class='modal-header alert alert-success' role='alert'>";
//                break;
//            case WARN:
//                ret = "<div class='modal-header alert alert-warning' role='alert'>";
//                break;
//            case INFO:
//                ret = "<div class='modal-header alert alert-info' role='alert'>";
//                break;
//            case ERROR:
//                ret = "<div class='modal-header alert alert-danger' role='alert'>";
//                break;
//        }
//
//        // FixMe: Path by assets.versioned ...
//        ret += "<img src='/assets/images/playcamp_logo_full_50.png' />";
//
//        ret += "<h5 class='pl-sm-3 modal-title' id='exampleModalLongTitle'>" + title + "</h5>";
//
//        ret += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'>";
//        ret += "<span aria-hidden='true'>&times;</span></button>";
//
//        ret += "</div><div class='modal-body'>";
//        ret += message;
//
//        ret += "</div><div class='modal-footer'>";
//        ret += "<button type='button' class='btn btn-secondary' data-dismiss='modal'>Schließen</button></div>";
//
//        return ret;
//    }
//
//    /**
//     * Get the skel for the bootstrap-modal-dialog.
//     * @return Returns the skel of the modal-dialog as String. To use in a Play-Template
//     * <pre>
//     * {@code
//     * @import utils.Dialog
//     * ...
//     * @Html(Dialog.getDialogSkel());
//     * }
//     * </pre>
//     */
//    public static String getDialogSkel(){
//        String ret = "<div class='modal fade' id='dialogModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>";
//        ret += "<div class='modal-dialog modal-dialog-centered' role='document'>";
//        ret += "<div class='modal-content' id='modalDialogContent'></div>";
//        ret += "</div></div>";
//        return ret;
//    }
}