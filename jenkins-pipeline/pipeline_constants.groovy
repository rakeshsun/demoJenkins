import groovy.transform.Field

@Field
def testngFilesList = 'testng-package.xml\ntestng-suite.xml\ntestng-production.xml\ntestng-specialprojects.xml\ntestng.xml\nctm-failed.xml'

@Field
def testngFilesList2 = 'testng-suite.xml\ntestng-production.xml\ntestng.xml'

@Field
def servicesTestngFilesList = 'testng.xml\ntestngSequential.xml'

@Field
def testdataFilesList = 'TestData.xml\nTestData-GmilProd.xml\nTestData-Prod.xml\nTestData-ProductInventory.xml\nTestDataCoremetrics.xml\nTestData-UAT.xml'

@Field
def svnId = '9d48cec4-e56a-4c80-bd18-e2b409f4e562'

@Field
def gitId = '34ab'

@Field
def wwwTestUrl = 'https://wwwsqs.officedepot.com/iphone/\nhttps://wwwsqe.officedepot.com/iphone/\nhttps://wwwsqm.officedepot.com/iphone/\nhttps://www.officedepot.com/\nhttps://wwwsln.officedepot.com/iphone/\nhttps://wwwsqp8.officedepot.com/iphone/\nhttps://wwwsqf8.uschecomrnd.net/iphone/\nhttps://wwwsqf.officedepot.com/iphone/\nhttps://wwwpoc.officedepot.com/iphone/'

@Field
def bsdTestUrl = 'https://bsdsqs.officedepot.com\nhttps://gmil.na.odcorp.net/\nhttps://bsdsqm.officedepot.com\nhttps://business.officedepot.com/\nhttps://bsdsln.officedepot.com/\nhttps://bsdsqp8.officedepot.com\nhttps://bsdsqf.officedepot.com\nhttps://gmilsqs.uschecomrnd.net/'


@Field
def sfdcTestUrl = 'https://partnerdev-officedepot-officemax.cs19.force.com/odPartner/\nhttps://oduat-officedepot-officemax.cs78.force.com/odPartner/'

@Field
def packagesNotNeeded = ['duplicate', 'BlockedTestCases', 'bsd','surefire', 'unReviewed']

@Field
def emailDistributionList = ['catalogSuccess':'khalid.saleem@officedepot.com,elena.nolan@officedepot.com,ecommateam@officedepot.com,niitsq@officedepot.com,e-commerce2@officedepot.com, rakesh.yadav@officedepot.com', 'catalogFailure':'amit.goyal@officedepot.com,Komal.Chauhan@officedepot.com,ecommateam@officedepot.com,Shivani.Bhardwaj@officedepot.com,rakesh.yadav@officedepot.com']

@Field
def mavenContainerVersion = 'maven354'

@Field
def mobilePlatformVersions = 'android-8.0.0\nandroid-7.1.1\nios-11.4\nios-11.3'

@Field
def mqHostName = 'MVSSYSD.officedepot.com\nrndfuture.uschecomrnd.net'

@Field
def mqPortNumber = '1426\n1414'

@Field
def mqQueueManager = 'MQQ6\nFUTURE'

@Field
def mqChannel = 'MICR_COS_OTC_MQQ6\nSOA_FUTURE'

@Field
def mqQueueName = 'US_COS_ITEM_FEED_TO_OTC\nODR_REQUEST\nAOPS_EAI_ODR_SYNC_REQUEST'

@Field
def packagesBSD = 'BSDLoginLogout\nFullChecklist\nBSDActivityLogVerifications\nBSDMultipleFileUploadCPD\nBSDMyFiles\nBSDNewSubscriptionManager\nBSDOrderEntry\nBSDShipComplete\nBSDStorePickup\nBSDThirdParty\ncheckout\nCreditDecline\nGMILScenarios\nHomePage\nCreditCards\nMultiCreditCard\nManageCreditCards\nPortalChecklist\nPortal_GS_LoginLogout\nMyFilesChecklist\nOnlineChat\nOrderEntryGMIL\nOrderHistory\nSearch\nShoppingCart\nSSO\nThirdPartyChecklist\nUAS\nWorkflow\nWorkplaceOrderMigration'

@Field
def packagesWWW = 'Checkout\nCoupon_Check\nCreditDecline\nCriticalE2EScenarios\nEndecaRules\nErsErpRegression\nFullChecklist\nGMILScenarios\nHomePage\nLoyalty\nMiscellaneous\nMixMode\nMyFilesChecklist\nOrderHistory\nOrderType\nPayPal\nSearch\nShoppingCart\ntechsubscriptionservice\nSpecialProjects\nThirdPartyChecklist\nUAS\nWWWMultipleFileUploadCPD\nWWWMyFiles\nWWWMyFilesActivityLogVerifications\nWWWThirdParty'


@Field
def packagesCPD ='BSDCPD_PrinterNet\nBSDFullCheckList\nBSDPickUpAfter2PM\nBSDPickUpBefore2PM\nCPDBSD\nCPDGMIL\nCPDWWW\nGMILCPD_PrinterNet\nGMILCPDFullChecklist\nGMILPickUpAfter2PM\nGMILPickUpBefore2PM\nGmilScenarios\nWWWCPD_PrinterNet\nwwwFullChecklist\nWWWPickUpAfter2PM\nWWWPickUpBefore2PM'

return this;
