package com.blauhill.mongodb.model;

import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Regression {

	public double slope;
	public double intercept;
		  
	public Regression(List<Property> properties) {
		
		SimpleRegression simpleRegression = new SimpleRegression(true);
		
		for (Property p : properties) {     
	         simpleRegression.addData(p.getGrLivArea(),p.getSalePrice());
	    }
	      
	    this.slope = simpleRegression.getSlope();
	    this.intercept = simpleRegression.getIntercept();
	}
}
