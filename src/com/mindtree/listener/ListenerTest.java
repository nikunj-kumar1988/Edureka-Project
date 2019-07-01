package com.mindtree.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mindtree.base.TestBase;

public class ListenerTest extends TestBase implements ITestListener {

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started->" + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Pass->" + result.getName());
		String methodName = "Passed_" + result.getMethod().getMethodName();
		takeScreenShot(methodName);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed->" + result.getName());
		String methodName = "Failed_" + result.getMethod().getMethodName();
		takeScreenShot(methodName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped->" + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("END Of Execution(TEST)->" + context.getName());
	}
}
