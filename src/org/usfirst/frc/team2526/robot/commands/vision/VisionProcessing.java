package org.usfirst.frc.team2526.robot.commands.vision;

import java.util.Collections;
import java.util.Vector;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessing {

	// Structure to represent the scores for the various tests used for target
	// identification
	public class Scores {
		double Trapezoid;
		double LongAspect;
		double ShortAspect;
		double AreaToConvexHullArea;
	};

	// Images
	Image frame;
	Image binaryFrame;
	int session;
	int imaqError;
	boolean active;

	// Constants
	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(24, 49); // Default hue
																// range for
																// yellow tote
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(67, 255); // Default
																	// saturation
																	// range for
																	// yellow
																	// tote
	NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(49, 255); // Default
																	// value
																	// range for
																	// yellow
																	// tote
	double AREA_MINIMUM = 0.5; // Default Area minimum for particle as a
								// percentage of total image area
	double LONG_RATIO = 2.22; // Tote long side = 26.9 / Tote height = 12.1 =
								// 2.22
	double SHORT_RATIO = 1.4; // Tote short side = 16.9 / Tote height = 12.1 =
								// 1.4
	double SCORE_MIN = 75.0; // Minimum score to be considered a tote
	double VIEW_ANGLE = 49.4; // View angle fo camera, set to Axis m1011 by
								// default, 64 for m1013, 51.7 for 206, 52 for
								// HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(
			0, 0, 1, 1);
	Scores scores = new Scores();

	public void init() {
		// create images
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(
				NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM,
				100.0, 0, 0);

		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		session = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		
		NIVision.IMAQdxConfigureGrab(session);

		// Put default values to SmartDashboard so fields will appear
		SmartDashboard.putNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
		SmartDashboard.putNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
		SmartDashboard.putNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
		SmartDashboard.putNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
		SmartDashboard.putNumber("Tote val min", TOTE_VAL_RANGE.minValue);
		SmartDashboard.putNumber("Tote val max", TOTE_VAL_RANGE.maxValue);
		SmartDashboard.putNumber("Area min %", AREA_MINIMUM);
	}

	public void start() {
		NIVision.IMAQdxStartAcquisition(session);
		active = true;
	}

	public void processFrame() {
		NIVision.IMAQdxGrab(session, frame, 1);

		

		// Update threshold values from SmartDashboard. For performance reasons
		// it is recommended to remove this after calibration is finished.
		TOTE_HUE_RANGE.minValue = (int) SmartDashboard.getNumber(
				"Tote hue min", TOTE_HUE_RANGE.minValue);
		TOTE_HUE_RANGE.maxValue = (int) SmartDashboard.getNumber(
				"Tote hue max", TOTE_HUE_RANGE.maxValue);
		TOTE_SAT_RANGE.minValue = (int) SmartDashboard.getNumber(
				"Tote sat min", TOTE_SAT_RANGE.minValue);
		TOTE_SAT_RANGE.maxValue = (int) SmartDashboard.getNumber(
				"Tote sat max", TOTE_SAT_RANGE.maxValue);
		TOTE_VAL_RANGE.minValue = (int) SmartDashboard.getNumber(
				"Tote val min", TOTE_VAL_RANGE.minValue);
		TOTE_VAL_RANGE.maxValue = (int) SmartDashboard.getNumber(
				"Tote val max", TOTE_VAL_RANGE.maxValue);

		// Threshold the image looking for yellow (tote color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255,
				NIVision.ColorMode.HSV, TOTE_HUE_RANGE, TOTE_SAT_RANGE,
				TOTE_VAL_RANGE);

		// Send particle count to dashboard
		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Masked particles", numParticles);

		// Send masked image to dashboard to assist in tweaking mask.
		CameraServer.getInstance().setImage(binaryFrame);

		// filter out small particles
		float areaMin = (float) SmartDashboard.getNumber("Area min %",
				AREA_MINIMUM);
		criteria[0].lower = areaMin;
		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame,
				criteria, filterOptions, null);

		// Send particle count after filtering to dashboard
		numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Filtered particles", numParticles);

		if (numParticles > 0) {
			// Measure particles and sort by particle size
			Vector<ParticleReport> particles = new Vector<ParticleReport>();
			for (int particleIndex = 0; particleIndex < numParticles; particleIndex++) {
				ParticleReport par = new ParticleReport();
				par.percentAreaToImageArea = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.area = NIVision.imaqMeasureParticle(binaryFrame,
						particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.convexHullArea = NIVision.imaqMeasureParticle(binaryFrame,
						particleIndex, 0,
						NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
				par.boundingRectTop = NIVision.imaqMeasureParticle(binaryFrame,
						particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.boundingRectLeft = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.boundingRectBottom = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				par.boundingRectRight = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				particles.add(par);
			}
			Collections.sort(particles);
			
			
			// This example only scores the largest particle. Extending to score
			// all particles and choosing the desired one is left as an exercise
			// for the reader. Note that the long and short side scores expect a
			// single tote and will not work for a stack of 2 or more totes.
			// Modification of the code to accommodate 2 or more stacked totes
			// is left as an exercise for the reader.
			
			scores.Trapezoid = TrapezoidScore(particles.elementAt(0));
			SmartDashboard.putNumber("Trapezoid", scores.Trapezoid);
			scores.LongAspect = LongSideScore(particles.elementAt(0));
			SmartDashboard.putNumber("Long Aspect", scores.LongAspect);
			scores.ShortAspect = ShortSideScore(particles.elementAt(0));
			SmartDashboard.putNumber("Short Aspect", scores.ShortAspect);
			scores.AreaToConvexHullArea = ConvexHullAreaScore(particles
					.elementAt(0));
			SmartDashboard.putNumber("Convex Hull Area",
					scores.AreaToConvexHullArea);
			boolean isTote = scores.Trapezoid > SCORE_MIN
					&& (scores.LongAspect > SCORE_MIN || scores.ShortAspect > SCORE_MIN)
					&& scores.AreaToConvexHullArea > SCORE_MIN;
			boolean isLong = scores.LongAspect > scores.ShortAspect;

			// Send distance and tote status to dashboard. The bounding rect,
			// particularly the horizontal center (left - right) may be useful
			// for rotating/driving towards a tote
			SmartDashboard.putBoolean("IsTote", isTote);
			SmartDashboard
					.putNumber(
							"Distance",
							computeDistance(binaryFrame,
									particles.elementAt(0), isLong));
		} else {
			SmartDashboard.putBoolean("IsTote", false);
		}
	}

	public void stop() {
		NIVision.IMAQdxStopAcquisition(session);
		active = false;
	}

	// Comparator function for sorting particles. Returns true if particle 1 is
	// larger
	static boolean CompareParticleSizes(ParticleReport particle1,
			ParticleReport particle2) {
		// we want descending sort order
		return particle1.percentAreaToImageArea > particle2.percentAreaToImageArea;
	}

	/**
	 * Converts a ratio with ideal value of 1 to a score. The resulting function
	 * is piecewise linear going from (0,0) to (1,100) to (2,0) and is 0 for all
	 * inputs outside the range 0-2
	 */
	double ratioToScore(double ratio) {
		return (Math.max(0, Math.min(100 * (1 - Math.abs(1 - ratio)), 100)));
	}

	/**
	 * Method to score convex hull area. This scores how "complete" the particle
	 * is. Particles with large holes will score worse than a filled in shape
	 */
	double ConvexHullAreaScore(ParticleReport report) {
		return ratioToScore((report.area / report.convexHullArea) * 1.18);
	}

	/**
	 * Method to score if the particle appears to be a trapezoid. Compares the
	 * convex hull (filled in) area to the area of the bounding box. The
	 * expectation is that the convex hull area is about 95.4% of the bounding
	 * box area for an ideal tote.
	 */
	double TrapezoidScore(ParticleReport report) {
		return ratioToScore(report.convexHullArea
				/ ((report.boundingRectRight - report.boundingRectLeft)
						* (report.boundingRectBottom - report.boundingRectTop) * .954));
	}

	/**
	 * Method to score if the aspect ratio of the particle appears to match the
	 * long side of a tote.
	 */
	double LongSideScore(ParticleReport report) {
		return ratioToScore(((report.boundingRectRight - report.boundingRectLeft) / (report.boundingRectBottom - report.boundingRectTop))
				/ LONG_RATIO);
	}

	/**
	 * Method to score if the aspect ratio of the particle appears to match the
	 * short side of a tote.
	 */
	double ShortSideScore(ParticleReport report) {
		return ratioToScore(((report.boundingRectRight - report.boundingRectLeft) / (report.boundingRectBottom - report.boundingRectTop))
				/ SHORT_RATIO);
	}

	/**
	 * Computes the estimated distance to a target using the width of the
	 * particle in the image. For more information and graphics showing the math
	 * behind this approach see the Vision Processing section of the
	 * ScreenStepsLive documentation.
	 *
	 * @param image
	 *            The image to use for measuring the particle estimated
	 *            rectangle
	 * @param report
	 *            The Particle Analysis Report for the particle
	 * @param isLong
	 *            Boolean indicating if the target is believed to be the long
	 *            side of a tote
	 * @return The estimated distance to the target in feet.
	 */
	double computeDistance(Image image, ParticleReport report, boolean isLong) {
		double normalizedWidth, targetWidth;
		NIVision.GetImageSizeResult size;

		size = NIVision.imaqGetImageSize(image);
		normalizedWidth = 2
				* (report.boundingRectRight - report.boundingRectLeft)
				/ size.width;
		targetWidth = isLong ? 26.0 : 16.9;

		return targetWidth
				/ (normalizedWidth * 12 * Math.tan(VIEW_ANGLE * Math.PI
						/ (180 * 2)));
	}
	
	public boolean isActive() {
		return active;
	}
}
