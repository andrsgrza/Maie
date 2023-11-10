/**
 * 
 */
/**
 * @author andre
 *
 */
module com.angaar.maie.service {
	requires transitive com.angaar.maie.handlers;
	requires com.angaar.maie.components;
	requires com.angaar.maie.util;
	exports com.angaar.maie.service.quizperfromer;
	exports com.angaar.maie.service.report;
}